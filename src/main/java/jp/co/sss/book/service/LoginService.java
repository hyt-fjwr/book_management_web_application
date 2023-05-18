package jp.co.sss.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.book.entity.BookUser;
import jp.co.sss.book.repository.BookUserRepository;

@Service
public class LoginService {
    @Autowired
    private BookUserRepository repo;
    
    public BookUser bookUser(Integer bookUserId, String password){
        BookUser user = repo.findByBookUserIdAndPassword(bookUserId, password);
        return user;
    }
}
