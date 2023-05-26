package jp.co.sss.book.controller;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //get all genres for the selector
    private List<Genre> getAllGenres() {
        return genreRepository.findAllByOrderByIdAsc();
    }

    //Main page
    @RequestMapping("/list")
    public String showList(Model model, Pageable pageable) {
        Page<Book> pages = repository.findAllWithPagenation(pageable);
        List<Book> bookList = pages.getContent();
        //Get all genres and then add them to the model.
        List<Genre> allGenres = getAllGenres();
        model.addAttribute("genres", allGenres);
        
        model.addAttribute("books", bookList);
        model.addAttribute("page", pages);
        return "/list_all_paging";
    }

    @RequestMapping("/list_r")
    public String redirect(Model model) {
        return "redirect:/list";
    }

    @PostMapping("/findByName")
    public String findByContainin(String bookName, Model model) {
        //Returns the main list if searched with nothing specified
        if (bookName == "") {
            return "redirect:/list";
        } else {
        //Get text from the form and then find books based on that text.
        model.addAttribute("books", repository.findByBookNameContaining(bookName));
        //Get all genres and then add them to the model.
        List<Genre> allGenres = getAllGenres();
        model.addAttribute("genres", allGenres);
        return "/list";
        }
    }
    
    @PostMapping("/findByGenre")
    public String findByGenre(Integer genreId, Model model){
        //Get genre value from the from and then find books based on that genre.
        model.addAttribute("books", repository.findByGenreId(genreId));
        model.addAttribute("genreId", genreId);
        //Get all genres and then add them to the model.
        List<Genre> allGenres = getAllGenres();
        model.addAttribute("genres", allGenres);
        return "/list";
    }
    
    //Display registry page
    @RequestMapping("/registry_book")
    public String registryBook(Model model){
        //Get all genres and then add them to the model.
        List<Genre> allGenres = getAllGenres();
        model.addAttribute("genres", allGenres);
        model.addAttribute("books", repository.findAllByOrderByBookIdAsc());
        return "book_registration";
    }

    //Data registry method
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
    
    //Display update page
    @RequestMapping("/update/{bookId}")
    public String updateInfo(@PathVariable Integer bookId, BookForm form, Model model){
    	//Create entity objects
        Book book = repository.findByBookId(bookId);
        BookBean bookBean = new BookBean();
        //Get all genres and then add them to the model.
        List<Genre> allGenres = getAllGenres();
        model.addAttribute("genres", allGenres);
        BeanUtils.copyProperties(book, bookBean);
        model.addAttribute("books", bookBean);
        return "/book_editor";
    }

    //Update book data in the database
    @PostMapping("/update/excute/{bookId}")
    public String updateComplete(@PathVariable Integer bookId, BookForm form, Model model){
        //Create entity objects.
        Book book = repository.getReferenceById(bookId);
        Genre genre = new Genre();
        //Get genre id from the form and then set genre id to the genre entity. 
        genre.setId(form.getGenreId());
        book.setGenre(genre);
        //Copy form value to the book entity exclude book id.
        BeanUtils.copyProperties(form, book, "id");
        //Save changed values to the database.
        book = repository.save(book);
        //unnessesary code??
        BookBean bookBean = new BookBean();
        BeanUtils.copyProperties(book, bookBean);
        return "redirect:/list";
    }

    //Delete book data from the database
    @PostMapping("/delete/excute/{bookId}")
    public String deleteComplete(@PathVariable Integer bookId, Model model){
            repository.deleteById(bookId);
            return "redirect:/list";
    }
}