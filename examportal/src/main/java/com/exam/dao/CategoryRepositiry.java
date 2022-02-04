package com.exam.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.exam.Category;

public interface CategoryRepositiry extends JpaRepository<Category, Long> {

}
