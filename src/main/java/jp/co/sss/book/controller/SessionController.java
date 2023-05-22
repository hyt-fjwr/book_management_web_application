package jp.co.sss.book.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.book.entity.BookUser;
import jp.co.sss.book.form.LoginForm;
import jp.co.sss.book.repository.BookUserRepository;

@Controller
public class SessionController {
	@Autowired
	private BookUserRepository userRepository;

	@RequestMapping(path = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// セッションの破棄
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/login")
	public String Login(Model model, @ModelAttribute LoginForm form){
		model.addAttribute("loginForm", form);
		return "index";
	}

	@PostMapping("/login")
	public String doLogin(Model model, @Valid @ModelAttribute LoginForm form, BindingResult result, HttpSession session, BookUser user){
		BookUser userData = userRepository.findByBookUserIdAndPassword(form.getBookUserId(), form.getPassword());
		BookUser userName = userRepository.findByBookUserId(form.getBookUserId());
		model.addAttribute("loginForm", form);
		if(result.hasErrors()){
			return "index";
		}
		if(userData != null) {
			session.setAttribute("userId", form.getBookUserId());
			session.setAttribute("userName", userName.getBookUserName());
			return "redirect:/list";
		} else {
			model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
			return "index" ;
		}
	}
}
