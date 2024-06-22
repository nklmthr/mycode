package com.nklmthr.finance.personal.dao;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category{
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	@Column
	private String id;

	@Column
	private String name;

	@Column
	private int level;
	
	@Column
	private boolean hidden;
	

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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return "Category [name=" + name + ", level=" + level + "]";
	}
}
