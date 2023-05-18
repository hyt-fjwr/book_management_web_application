package jp.co.sss.book.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class LoginForm {

	@NotNull
	@Max(value = 99999)
	private Integer bookUserId;
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String password;

	public Integer getBookUserId() {
		return bookUserId;
	}

	public void setBookUserId(Integer bookUserId) {
		this.bookUserId = bookUserId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
