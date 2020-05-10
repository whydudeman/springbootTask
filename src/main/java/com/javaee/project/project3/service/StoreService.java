package com.javaee.project.project3.service;

import com.javaee.project.project3.form.StoreForm;
import com.javaee.project.project3.model.Store;
import com.javaee.project.project3.repository.StoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    private StoreRepo storeRepo;
    @Autowired
    private CityService cityService;

    public List<Store> getAll() {
        return storeRepo.findAll();
    }

    public Store createByStoreForm(StoreForm storeForm) {
        Store store = new Store();
        store.setAddress(storeForm.getAddress());
        store.setCity(cityService.getById(storeForm.getCityId()));
        store.setName(storeForm.getName());
        return storeRepo.save(store);

    }

    public void deleteById(Long id) {
        storeRepo.deleteById(id);
    }

    public void update(Long id, StoreForm storeForm) {
        Store store = getStoreById(id);
        store.setAddress(storeForm.getAddress());
        store.setCity(cityService.getById(storeForm.getCityId()));
        store.setName(storeForm.getName());
        storeRepo.save(store);
    }

    public List<Store> getAllByCity(Long cityId) {
        return storeRepo.getAllByCity(cityId);
    }

    public Store getStoreById(Long storeId) {
        return storeRepo.findById(storeId).orElseThrow(() -> new RuntimeException("NOT_FOUND"));
    }
}
