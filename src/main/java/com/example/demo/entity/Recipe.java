package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id; //レシピID
	private String name; //料理名
	private String recipe; //レシピ
	@Column(name = "user_id")
	private Integer userId; //ユーザーID

	@ManyToOne
	@JoinColumn(name = "category_id") //カテゴリー
	private Category category;
	//private String recipeDetail;

	public Recipe() {
	}

	// コンストラクタ
	public Recipe(Integer id, String name, String recipe, Integer userId, Category category) {
		this.id = id;
		this.name = name;
		this.recipe = recipe;
		this.userId = userId;
		this.category = category;

	}

	/*public Recipe(String name, Integer userId, Category category, String recipeDetail) {
		this.name = name;
		this.userId = userId;
		this.category = category;
		this.recipeDetail = recipeDetail;
	}
	*/
	//ゲッターセッター

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getRecipe() {
		return recipe.replaceAll("\n", "<br>");
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/*	public String getRecipeDetail() {
			return recipeDetail;
		}
	
		public void setRecipeDetail(String recipeDetail) {
			this.recipeDetail = recipeDetail;
		}*/

}
