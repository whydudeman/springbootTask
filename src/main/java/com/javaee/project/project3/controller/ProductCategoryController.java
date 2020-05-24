package com.javaee.project.project3.controller;

import com.javaee.project.project3.form.FieldName;
import com.javaee.project.project3.model.User;
import com.javaee.project.project3.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "categorys")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping
    public String getAll(Model model) {
        FieldName category = new FieldName();
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", category);
        return "category/categories";
    }

    @PostMapping("create")
    public String create(@ModelAttribute FieldName category) {
        categoryService.create(category.getField());
        return "redirect:";
    }

    @GetMapping(value = "delete/{id}")
    public String deleteCategory(@PathVariable(name = "id") Long id, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User sds = ((User) principal);
        if (sds.getRoles().stream().anyMatch(o->o.getRole().contains("ROLE_ADMIN")))
            categoryService.deleteById(id);
        return getAll(model);
    }

    @GetMapping(value = "edit/{id}")
    public String editCategory(@PathVariable(name = "id") Long id, Model model) {
        FieldName category = new FieldName();
        model.addAttribute("categoryId", id);
        model.addAttribute("category", category);
        return "category/editCategory";
    }

    @PostMapping(value = "edit/{id}")
    public String saveCategory(@PathVariable(name = "id") Long id, @ModelAttribute FieldName category, Model model) {

        categoryService.updateCategory(id, category.getField());
        return getAll(model);
    }


}
