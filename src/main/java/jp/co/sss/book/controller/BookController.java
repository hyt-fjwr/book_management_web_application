package jp.co.sss.book.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.book.entity.Book;
import jp.co.sss.book.form.BookForm;
import jp.co.sss.book.repository.BookRepository;
import jp.co.sss.book.repository.BookUserRepository;
import jp.co.sss.book.repository.GenreRepository;


@Controller
public class BookController {

    @Autowired
    BookRepository repository;
    @Autowired
    BookUserRepository userRepository;
    @Autowired
    GenreRepository genreRepository;


    @RequestMapping("/list")
    public String showList(Model model) {
        model.addAttribute("genres", genreRepository.findAllByOrderByIdAsc());
        return "/list";
    }

    @RequestMapping("/list_r")
    public String redirect(Model model) {
        return "redirect:/list";
    }

    @RequestMapping("/findAll")
    public String showList(Model model, String user) {
        model.addAttribute("genres", genreRepository.findAllByOrderByIdAsc());
        model.addAttribute("books", repository.findAllByOrderByBookIdAsc());
        return "/list";
    }

    @PostMapping("/findByName")
    public String findByContainin(String bookName, Model model) {
        model.addAttribute("books", repository.findByBookNameContaining(bookName));
        model.addAttribute("genres", genreRepository.findAllByOrderByIdAsc());
        return "/list";
    }

    @PostMapping("/findByGenre")
    public String findByGenre(Integer genreId, Model model){
        model.addAttribute("books", repository.findByGenreId(genreId));
        model.addAttribute("genres", genreRepository.findAllByOrderByIdAsc());
        return "/list";
    }

    @RequestMapping("/registry_book")
    public String registryBook(Model model){
        model.addAttribute("genres", genreRepository.findAllByOrderByIdAsc());
        return "book_registration";
    }

    @PostMapping("/registry_complete")
    public String registryComplete(BookForm form, Model model){
        Book book = new Book();
        BeanUtils.copyProperties(form, book);
        System.out.println("BookID"+form.getBookId());
        System.out.println("作品名"+form.getBookName());
        System.out.println("著者"+form.getAuthor());
        System.out.println("日にち"+form.getPublicationDate());
        System.out.println("ストック"+form.getStock());
        System.out.println("ジャンルID"+form.getGenreId());
        return "redirect:/findAll";
    }
}