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
import jp.co.sss.book.service.LoginService;

@Controller
public class SessionController {
	@Autowired
	private BookUserRepository userRepository;
	@Autowired
	private LoginService userService;

	// @GetMapping("/login")
	// public ModelAndView login(){
	// 	ModelAndView mav = new ModelAndView("login");
	// 	mav.addObject("user", new BookUser());
	// 	return mav;
	// }

	// @PostMapping("/login")
	// public String login(@Valid @ModelAttribute("user") BookUser user, Model model, HttpSession session, LoginForm form){
	// 	BookUser oauthUser = userService.bookUser(user.getBookUserId(), user.getPassword());
	// 	Integer userId = Integer.parseInt(form.getBookUserId());
	// 	BookUser userName = userRepository.findByBookUserId(userId);

	// 	System.out.println("------oauthUser------");
	// 	System.out.println(oauthUser);
	// 	if(Objects.nonNull(oauthUser)){
	// 		session.setAttribute("user", user.getBookUserId());
	// 		session.setAttribute("userName", userName.getBookUserName());
	// 		return "redirect:/list";
	// 	} else {
	// 		model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
	// 		return "windex";
	// 	}
	// }


	// @RequestMapping(path = "/login", method = RequestMethod.POST)
	// public String doLogin(BookUser userEntity, LoginForm form, UserForm userForm, HttpSession session, Model model) {

	// 	Integer userId = Integer.parseInt(form.getBookUserId());
	// 	String password = form.getPassword();
	// 	BookUser user = userRepository.findByBookUserIdAndPassword(userId, password);
	// 	BookUser userName = userRepository.findByBookUserId(userId);
	// 	if (user != null) {
	// 		session.setAttribute("user", userId);
	// 		session.setAttribute("userName", userName.getBookUserName());
	// 		return "redirect:/list";

	// 	} else {
	// 		model.addAttribute("errMessage", "ユーザID、またはパスワードが間違っています。");
	// 		return "index";
	// 	}
	// }

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
