package Member;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Library.Book;
import Library.Library;

public class Teacher extends Member {

	private Scanner sc;
	//has-a の関係で　TeacherがBookを持たないといけないので
	private List<Book> teacherBookList = new ArrayList<>();
	private Library library;

	public Teacher(String name, int id, Scanner sc, Library library) {
		super(name, id);
		this.sc = sc;
		this.library = library;
	}

	@Override
	public void borrow() {

		if (teacherBookList.size() >= 5) {
			System.out.println("もう、借りられません");
			return;
		}

		borrowLoop: while (true) {

			System.out.println("先生、何を借りますか?");
			System.out.println("借りられるのは、５冊まで");
			System.out.println("借りたい本の、IDを入力してくださいい");

			List<Book> bookList = library.getBookList();

			for (Book book : bookList) {
				if(book.getBorrowed()) {
					System.out.println("貸出中");
				}else {
					System.out.println(book.getId() + ":" + book.getName());
				}
			
			}

			while (!sc.hasNextInt()) {
				System.out.println("数値を入力してください");
				sc.next();
			}

			int index = sc.nextInt();

			for (Book book : bookList) {
				if (book.getId() == index) {
					if (book.getBorrowed()) {
						System.out.println("その本は、すでに借りられています"
								+ "やりなおしてください");
						break;
					}

					System.out.println(book.getName() + "を借りました");
					book.setBorrowed();
					teacherBookList.add(book);//本を貯める

					break borrowLoop;//whileまで強制終了
				}

			}

			// ★ forを最後まで探してもIDがなかった場合だけここに来る
			System.out.println("借りられませんでした");
			break borrowLoop;

		}

	}
}
