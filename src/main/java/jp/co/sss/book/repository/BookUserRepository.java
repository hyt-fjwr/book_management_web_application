package jp.co.sss.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import jp.co.sss.book.entity.BookUser;

public interface BookUserRepository extends JpaRepository<BookUser, Integer> {
	BookUser findByBookUserIdAndPassword(Integer bookUserId, String password);
	BookUser findByBookUserId(Integer BookUserId);
}
