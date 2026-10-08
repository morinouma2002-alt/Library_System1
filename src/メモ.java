/*アンモディファイアブル・リスト」です。

unmodifiable=「変更できない」（un+modifiable）Collections.unmodifiableList(...)は「コレクションズ・ドット・アンモディファイアブルリスト」と読みます

その理解で合っています。整理すると、こうなります。

getter で返したリストは、外側が add/remove/clear で勝手に書き換えられる可能性がある それを防ぐために、「読み取り専用」の包みをかぶせて返す 外側が書き換えようとすると UnsupportedOperationException で止まる

使うときの形は次のとおりです。

java

import java.util.Collections; // これを追加

public List<Book>getBookList(){return Collections.unmodifiableList(bookList);}

public List<Member>getMemberList(){return Collections.unmodifiableList(memberList);}

import は java.util.Collections の1行を足すだけです。

注意点が1つあります。unmodifiableList が止めるのは リストの中身の出し入れ（追加・削除）だけです。リストの中にいる Book や Member 自体は、setter があれば外から変えられます。今の Book は setter が無いので問題ありませんが、あとで Member に「借りた本のリスト」を持たせるときは、同じ考え方をそこにも当てはめる必要があります。

あと、setMemberList を addMember に変える件も、使うタイミングでついでに直しておくと名前と動作が合います（SystemMain 側の呼び出しも変えてください）。
*/