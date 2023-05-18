package jp.co.sss.book.form;

public class UserForm {

	private Integer bookUserId;
	private String bookUserName;
	private String password;
    
    public Integer getBookUserId() {
        return bookUserId;
    }
    public void setBookUserId(Integer bookUserId) {
        this.bookUserId = bookUserId;
    }
    public String getBookUserName() {
        return bookUserName;
    }
    public void setBookUserName(String bookUserName) {
        this.bookUserName = bookUserName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
