INSERT INTO genre VALUES(4, '生活');
INSERT INTO book VALUES(5, '野菜は食べなくてもいい', '堀江貴文', '2013/3/13', 8, 4);
commit;

delete from book where book_id = 5;