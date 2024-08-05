package com.nklmthr.finance.personal.dao;

import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nklmthr.finance.personal.service.CategoryType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@UuidGenerator
	private String id;

	@Column
	private String name;

	@Column
	private int level;

	@Enumerated(EnumType.STRING)
	private CategoryType categoryType;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category", referencedColumnName = "id")
	private Category parentCategory;

	@JsonIgnore
	@OneToMany(mappedBy = "parentCategory", cascade = CascadeType.ALL)
	private Set<Category> childCategorys;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Set<Category> getChildCategorys() {
		return childCategorys;
	}

	public void setChildCategorys(Set<Category> childCategorys) {
		this.childCategorys = childCategorys;
	}

	public CategoryType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", level=" + level + ", categoryType=" + categoryType + "]";
	}

}
