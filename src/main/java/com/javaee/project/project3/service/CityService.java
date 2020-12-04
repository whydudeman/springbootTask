package com.javaee.project.project3.service;

import com.javaee.project.project3.model.City;
import com.javaee.project.project3.repository.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {
    @Autowired
    private CityRepo cityRepo;

    public List<City> getAll() {
        return cityRepo.findAll();
    }

    public City getById(Long id){
       return cityRepo.findById(id).orElseThrow(()-> new RuntimeException("NO_CITY_FOUND"));
    }
}
