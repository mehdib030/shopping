package com.store.retail.category.service;

import com.store.retail.category.model.Category;
import com.store.retail.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name).get(0);
    }
}
