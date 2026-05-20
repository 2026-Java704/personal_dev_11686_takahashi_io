package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
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
		if (email.length() == 0 || password.length() == 0) {
			model.addAttribute("message", "入力して下さい");
			return "login";
		}

		List<User> userList = userRepository.findByEmailAndPassword(email, password);
		if (userList == null || userList.size() == 0) {
			// 存在しなかった場合
			model.addAttribute("message", "メールアドレスとパスワードが一致しませんでした");
			return "login";

		}
		User user = userList.get(0);
		// セッション管理されたアカウント情報にIDと名前をセット

		account.setEmail(user.getEmail());

		return "Recipes";
	}

	// 会員登録画面の表示
	@GetMapping("/users/new")
	public String create() {
		return "Account";

	}

	// 会員登録実行
	@PostMapping("/users/add")
	public String add(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String password,
			//@RequestParam String passwordConfirm,
			Model model) {
		// エラーチェック
		List<String> errorList = new ArrayList<>();
		if (name.length() == 0) {
			errorList.add("名前は必須です");
		}

		if (email.length() == 0) {
			errorList.add("メールアドレスは必須です");
		}
		// メールアドレス存在チェック
		List<User> userList = userRepository.findByEmail(email);
		if (userList != null && userList.size() > 0) {
			// 登録済みのメールアドレスが存在した場合
			errorList.add("登録済みのメールアドレスです");
		}
		if (password.length() == 0) {
			errorList.add("パスワードは必須です");
		}

		// エラー発生時はお問い合わせフォームに戻す
		if (errorList.size() > 0) {
			model.addAttribute("errorList", errorList);
			model.addAttribute("name", name);
			model.addAttribute("email", email);
			model.addAttribute("password", password);

			return "Account";
		}

		User user = new User(name, email, password);
		userRepository.save(user);

		return "redirect:/";
	}
}