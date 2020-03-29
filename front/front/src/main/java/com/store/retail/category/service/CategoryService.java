package com.store.retail.category.service;

import com.store.retail.category.model.Category;

import java.util.List;

public interface CategoryService {

    Category findCategoryByName(String name);
}
