package com.library.service;

import java.util.List;

import com.library.dto.CategoryDTO;

public interface CategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDTO);
    List<CategoryDTO> getAllCategories();
}

