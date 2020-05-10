package com.javaee.project.project3.service;

import com.javaee.project.project3.model.ProductCategory;
import com.javaee.project.project3.repository.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepo categoryRepo;

    public List<ProductCategory> getAllCategories(){
       return categoryRepo.findAll();
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }

    public ProductCategory updateCategory(Long id, String name) {
        ProductCategory category=getCategoryById(id);
        category.setName(name);
        return categoryRepo.save(category);
    }

    public ProductCategory create(String name) {
        ProductCategory category=new ProductCategory();
        category.setName(name);
        return categoryRepo.save(category);
    }

    public ProductCategory getCategoryById(Long id){
        return categoryRepo.findById(id).orElseThrow(()->new RuntimeException("NO_CATEGORY_FOUND"));
    }

}
