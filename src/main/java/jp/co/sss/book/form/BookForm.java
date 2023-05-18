package jp.co.sss.book.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookForm {
    private Integer bookId;
    private String bookName;
    private String author;
    private Date publicationDate;
    private Integer stock;
    private Integer genreId;
    
    public Integer getBookId() {
        return bookId;
    }
    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(String publicationDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            this.publicationDate = dateFormat.parse(publicationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Integer getStock() {
        return stock;
    }
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public Integer getGenreId() {
        return genreId;
    }
    
    public void setGenreId(String genreId) {
        this.genreId = Integer.parseInt(genreId);
    }
    
}
