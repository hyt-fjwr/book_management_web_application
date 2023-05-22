package jp.co.sss.book.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sss.book.bean.BookBean;
import jp.co.sss.book.entity.Book;
import jp.co.sss.book.entity.Genre;
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
        model.addAttribute("books", repository.findAllByOrderByBookIdAsc());
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
        model.addAttribute("books", repository.findAllByOrderByBookIdAsc());
        return "book_registration";
    }

    @PostMapping("/registry_complete")
    public String registryComplete(BookForm form, Model model){
    	//Create entity objects
    	Book book = new Book();
    	Genre genre = new Genre();
    	//Set genre id to the genre entity
    	genre.setId(form.getGenreId());
    	book.setGenre(genre);
    	
    	//Copy form value to the book entity exclude book id.
        BeanUtils.copyProperties(form, book, "id");
        
        //Registry data to the database
        book=repository.save(book);
        return "redirect:/registry_book";
    }

    @RequestMapping("/update/{bookId}")
    public String updateInfo(@PathVariable Integer bookId, BookForm form, Model model){
        Book book = repository.findByBookId(bookId);
        BookBean bookBean = new BookBean();
        model.addAttribute("genres", genreRepository.findAllByOrderByIdAsc());
        BeanUtils.copyProperties(book, bookBean);
        model.addAttribute("books", bookBean);
        return "/book_editor";
    }

    @PostMapping("/update/excute/{bookId}")
    public String updateComplete(@PathVariable Integer bookId, BookForm form, Model model){
        Book book = repository.getReferenceById(bookId);
        Genre genre = new Genre();

        genre.setId(form.getGenreId());
        book.setGenre(genre);

        BeanUtils.copyProperties(form, book, "id");
        book = repository.save(book);
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(book, bookBean);
        return "redirect:/list";
    }

    @PostMapping("/delete/excute/{bookId}")
    public String deleteComplete(@PathVariable Integer bookId, Model model){
            repository.deleteById(bookId);
            return "redirect:/list";
        }
}