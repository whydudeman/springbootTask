package com.javaee.project.service;

import com.javaee.project.dto.ToyDto;
import com.javaee.project.model.Category;
import com.javaee.project.model.Toy;
import com.javaee.project.repositoty.ToyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToyService {
    @Autowired
    private ToyRepo toyRepo;
    @Autowired
    private CategoryService categoryService;
    public Toy save(Toy toy) {
        return toyRepo.save(toy);
    }

    public Toy saveByDto(ToyDto toyDto){
        Toy toy=new Toy();
        Category category=categoryService.getCategoryById(toyDto.getCategoryId());
        if(toyDto.getCategoryId()!=null){
            toy.setId(toyDto.getId());
        }
        toy.setCategory(category);
        toy.setName(toyDto.getName());
        toy.setPrice(toyDto.getPrice());
       return toyRepo.save(toy);
    }

    public List<Toy> getAll() {
        return toyRepo.findAll();
    }

    public Toy getToyById(Long id) {
        return toyRepo.findById(id).orElseThrow(()->new RuntimeException("Toy with this id doesnt exists"));
    }

    public void deleteById(Long id) {
        if(!toyRepo.existsById(id)){
            throw new RuntimeException("No toy found by given id!!!");
        }
        toyRepo.deleteById(id);
    }

    public ToyDto getToyDtoById(Long id) {
        ToyDto toyDto = new ToyDto();
        Toy toy=getToyById(id);
        toyDto.setId(toy.getId());
        toyDto.setName(toy.getName());
        toyDto.setPrice(toyDto.getPrice());
        toyDto.setCategoryId(toy.getCategory().getId());
        return toyDto;
    }

    public Double getTotalPrice(List<Toy> toys){
        Double totalPrice=0.0;
        for (Toy toy: toys){
            totalPrice+=toy.getPrice();
        }
        return totalPrice;
    }
}
