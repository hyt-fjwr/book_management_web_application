package jp.co.sss.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.co.sss.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	//Find all data order by ascending.
    List<Book> findAllByOrderByBookIdAsc();

    List<Book> findByBookNameContaining(String bookName);
    
    List<Book> findByGenreId(Integer genreId);
}
