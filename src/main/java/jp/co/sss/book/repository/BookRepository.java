package jp.co.sss.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jp.co.sss.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	//Find all data order by ascending.
    List<Book> findAllByOrderByBookIdAsc();

    List<Book> findByBookNameContaining(String bookName);
    
    List<Book> findByGenreId(Integer genreId);

    Book findByBookId(Integer bookId);
    
    //Find all method for paging
    @Query(value = "select * from book ORDER BY book_id", countQuery = "SELECT count(*) FROM book", nativeQuery = true)
    Page<Book> findAllWithPagenation(Pageable pageable);
}
