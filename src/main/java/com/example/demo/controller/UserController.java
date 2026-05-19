package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	private final HttpSession session;
	private final UserRepository userRepository;

	public UserController(
			HttpSession session,
			UserRepository userRepository) {

		this.session = session;
		this.userRepository = userRepository;
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
		/*if (email == null || email.length() == 0) {
			model.addAttribute("message", "入力して下さい");
		
		}*/
		return "login";
	}
}
