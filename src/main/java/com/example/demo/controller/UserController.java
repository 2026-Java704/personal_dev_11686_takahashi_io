package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Account;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	private final HttpSession session;
	private final UserRepository userRepository;
	private final Account account;

	public UserController(
			HttpSession session,
			UserRepository userRepository,
			Account account) {

		this.session = session;
		this.userRepository = userRepository;
		this.account = account;
	}

	//ログイン画面表示
	@GetMapping({ "/", "/login", "/logout" })
	public String index() {
		//セッション情報を破棄
		session.invalidate();
		return "login";

	}

	//ログインを実行する
	@PostMapping("/login")
	public String login(
			@RequestParam String email,
			@RequestParam String password,
			Model model) {
		if (email == null || email.length() == 0) {
			model.addAttribute("message", "入力して下さい");

		}
		return "login";
	}

	// 会員登録画面の表示
	@GetMapping("/users/new")
	public String create() {
		return "Account";

	}
}
