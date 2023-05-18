-- ジャンル情報テーブルの作成
CREATE TABLE genre (
  genre_id NUMBER(2) PRIMARY KEY,
  genre_name VARCHAR(30 CHARACTERS) NOT NULL
);

-- 書籍情報テーブルの作成
CREATE TABLE book (
  book_id NUMBER(5) PRIMARY KEY,
  book_name VARCHAR(60 CHARACTERS) NOT NULL,
  author VARCHAR(30 CHARACTERS) NOT NULL,
  publication_date DATE NOT NULL,
  stock NUMBER(4) NOT NULL,
  genre_id NUMBER(2) NOT NULL REFERENCES genre(genre_id)
);

-- ユーザ情報テーブルの作成
CREATE TABLE book_user  (
  book_user_id NUMBER(5) PRIMARY KEY,
  book_user_name VARCHAR(30 CHARACTERS) NOT NULL,
  password VARCHAR(16 CHARACTERS) NOT NULL
);