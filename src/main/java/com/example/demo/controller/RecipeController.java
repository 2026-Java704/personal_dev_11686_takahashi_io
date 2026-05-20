package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Recipe;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RecipeRepository;

@Controller
public class RecipeController {

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;

	public RecipeController(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
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

		// 商品一覧情報の取得
		List<Recipe> recipeList = null;

		if (categoryId != null) {

			//レシピテーブル
			recipeList = recipeRepository.findByCategory(categoryId);
		} else if (keyword.length() > 0) {
			// 商品名による部分一致検索 
			//itemList = itemRepository.findByNameLike("%" + keyword + "%");  //  // Likeを利用した場合は「%」が必要です
			recipeList = recipeRepository.findByNameContaining(keyword);

		} else {
			// 全商品検索
			recipeList = recipeRepository.findAll();
		}

		model.addAttribute("keyword", keyword);
		model.addAttribute("recipe", recipeList);

		return "Recipes";

	}
}