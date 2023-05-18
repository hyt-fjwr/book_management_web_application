-- ジャンル情報テーブルへのレコード登録
INSERT INTO genre VALUES(1, '文学');
INSERT INTO genre VALUES(2, '経済');
INSERT INTO genre VALUES(3, '技術');

-- 書籍情報テーブルへのレコード登録
INSERT INTO book VALUES(1, 'よくわかる経済', '山田健介', '2010-11-03', 10, 2);
INSERT INTO book VALUES(2, '日本文学傑作選', '大原洋子', '1998-03-05',  3, 1);
INSERT INTO book VALUES(3, '日本経済史2016', '宮本良太', '2016-07-20', 23, 2);

-- ユーザ情報テーブルへのレコード登録
INSERT INTO book_user VALUES(1, '鈴木太郎', 1111);
INSERT INTO book_user VALUES(2, '渡辺花子', 2222);


COMMIT;