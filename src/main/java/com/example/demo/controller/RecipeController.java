package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Recipe;
import com.example.demo.model.Account;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RecipeRepository;

@Controller
public class RecipeController {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;

	private final Account account;

	public RecipeController(CategoryRepository categoryRepository,
			RecipeRepository recipeRepository,
			Account account) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
		this.account = account;
	}

	// 商品一覧表示
	@GetMapping("/recipes")
	public String index(

			@RequestParam(defaultValue = "") Integer categoryId,
			@RequestParam(defaultValue = "") String keyword,
			Model model) {

		// categoryテーブルから全カテゴリー一覧を取得
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		// レシピ一覧情報の取得
		List<Recipe> recipeList = null;
		if (account.getId() == null) {
			return "redirect:/login";
		}

		if (categoryId != null) {
			//カテゴリーIDを指定して一覧取得
			recipeList = recipeRepository.findByCategory_Id(categoryId);

		} else if (keyword.length() > 0) {
			// 商品名による部分一致検索 

			recipeList = recipeRepository.findByNameContaining(keyword);

		} else {
			recipeList = recipeRepository.findAll();
			// 全商品検索

		}
		model.addAttribute("recipes", recipeList);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("keyword", keyword);

		return "recipes";

	}

	//レシピ新規作成
	@GetMapping("/recipes/add")
	public String create() {

		if (account.getId() == null) {
			return "redirect:/login";
		}
		return "addRecipes";
	}

	//新規登録処理
	@PostMapping("/recipes/add")
	public String add(
			@RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "") String detail,
			@RequestParam(defaultValue = "") Integer userId,
			@RequestParam(defaultValue = "") Category category) {

		Recipe recipes = new Recipe(name, detail, userId, category);

		recipeRepository.save(recipes);
		if (account.getId() == null) {
			return "redirect:/login";
		}

		return "redirect:/recipes";

	}

	//レシピを取得
	@GetMapping("/recipes/detail/{id}")
	public String detail(
			@PathVariable Integer id,
			Model model) {
		Recipe recipe = recipeRepository.findById(id).get();
		model.addAttribute("recipe", recipe);

		if (account.getId() == null) {
			return "redirect:/login";
		}

		return "recipeDetail";
	}
}
