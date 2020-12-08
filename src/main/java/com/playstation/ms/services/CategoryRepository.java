package com.playstation.ms.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.playstation.ms.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
