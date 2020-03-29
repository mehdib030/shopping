package com.store.retail.category.controller;

import com.store.retail.category.model.Category;
import com.store.retail.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/category/products/{name}")
    public @ResponseBody Category readCategory(@PathVariable String name){

        return categoryService.findCategoryByName(name);

    }
}
