package com.javaee.project.project1.service;

import com.javaee.project.project1.model.Category;
import com.javaee.project.project1.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    public List<Category> getAll() {
        return categoryRepo.findAll();
    }

    public Category createByName(String name) {
        Category category=new Category();
        category.setName(name);
        return categoryRepo.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(()->new RuntimeException("No category found by given id!!!"));
    }

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteById(Long id) {
        if(!categoryRepo.existsById(id)){
            throw new RuntimeException("No category found by given id!!!");
        }
        categoryRepo.deleteById(id);
    }

}
