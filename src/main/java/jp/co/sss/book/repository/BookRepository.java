package jp.co.sss.book.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jp.co.sss.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{
	//Find all data order by ascending.
    List<Book> findAllByOrderByBookIdAsc();

    @Query(value = "SELECT * FROM book i WHERE i.book_name LIKE %:bookName% ORDER BY book_id" , countQuery = "SELECT count(*) FROM book", nativeQuery = true)
    List<Book> findByBookNameContaining(@Param("bookName") String bookName);
    
    List<Book> findByGenreId(Integer genreId);

    Book findByBookId(Integer bookId);
    
    //Find all method for paging
    @Query(value = "SELECT * FROM book ORDER BY book_id", countQuery = "SELECT count(*) FROM book", nativeQuery = true)
    Page<Book> findAllWithPagenation(Pageable pageable);
}
