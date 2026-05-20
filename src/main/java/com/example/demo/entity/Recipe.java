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
	private String recipes; //レシピ
	@Column(name = "user_id")
	private Integer userId; //ユーザーID

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	public Recipe() {
	}

	// コンストラクタ
	public Recipe(Integer id, String name, String recipes, Integer userId, Category category, String recipe) {
		this.id = id;
		this.name = name;
		this.recipes = recipes;
		this.userId = userId;
		this.category = category;

	}

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

	public String getrecipes() {
		return recipes;
	}

	public void setId(String recipes) {
		this.recipes = recipes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
