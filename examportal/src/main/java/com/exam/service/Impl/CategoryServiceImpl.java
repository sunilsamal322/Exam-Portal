package com.exam.service.Impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.dao.CategoryRepositiry;
import com.exam.model.exam.Category;
import com.exam.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepositiry categoryRepositiry;
	
	@Override
	public Category addCategory(Category category) {
		
		return categoryRepositiry.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		
		return categoryRepositiry.save(category);
	}

	@Override
	public Set<Category> getCategories() {
		
		return new LinkedHashSet<>(categoryRepositiry.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) {
		
		return categoryRepositiry.findById(categoryId).get();
	}

	@Override
	public void deleteCategory(Long categoryId) {
		
		Category category=new Category();
		category.setCid(categoryId);
		
		this.categoryRepositiry.delete(category);
		
	}

}
