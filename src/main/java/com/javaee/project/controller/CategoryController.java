package com.javaee.project.controller;

import com.javaee.project.dto.CategoryDto;
import com.javaee.project.dto.ToyDto;
import com.javaee.project.model.Category;
import com.javaee.project.model.Toy;
import com.javaee.project.service.CategoryService;
import com.javaee.project.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ToyService toyService;

    @GetMapping(value="index")
    public String getAllCategories(Model model) {

        CategoryDto formCategory=new CategoryDto();
        ToyDto toy=new ToyDto();
        List<Category> categories=categoryService.getAll();
        List<Toy> toys=toyService.getAll();
        model.addAttribute("categories",categories);
        model.addAttribute("toys",toys);
        model.addAttribute("totalPrice",toyService.getTotalPrice(toys));

        model.addAttribute("formCategory",formCategory);
        model.addAttribute("formToy",toy);

        return "index";
    }
    @PostMapping(value = "createToy")
    public String createToy(@ModelAttribute ToyDto toyDto){
        Toy toy1=toyService.saveByDto(toyDto);
        return "redirect:/index";
    }

    @PostMapping(value="create")
    public String create(@ModelAttribute CategoryDto categoryDto){

        String name=categoryDto.getName();
        categoryService.createByName(name);
        return "redirect:/index";
    }

    @GetMapping(value = "edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Category category=categoryService.getCategoryById(id);
        model.addAttribute("category",category);
        return "categoryedit";
    }
    @PostMapping(value = "update/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute Category category) {
        category.setId(id);
        categoryService.save(category);
        return "redirect:/index";
    }
    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable("id") Long id,Model model){
        categoryService.deleteById(id);
        return "redirect:/index";
    }
//toy
    @GetMapping(value = "editToy/{id}")
    public String showUpdateFormForToy(@PathVariable("id") Long id, Model model){
        Toy toy=toyService.getToyById(id);
        ToyDto toyDto=toyService.getToyDtoById(id);
        List<Category> categories=categoryService.getAll();
        model.addAttribute("toy",toyDto);
        model.addAttribute("categories",categories);
        return "toyedit";
    }
    @PostMapping(value = "updateToy/{id}")
    public String updateToy(@PathVariable("id") Long id, @ModelAttribute ToyDto toy) {
        System.out.println(toy.getPrice()+" this is price");
        toyService.saveByDto(toy);

        return "redirect:/index";
    }
    @GetMapping(value = "deleteToy/{id}")
    public String deleteToy(@PathVariable("id") Long id,Model model){
        toyService.deleteById(id);
        return "redirect:/index";
    }


}