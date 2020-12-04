package com.javaee.project.project3.controller;

import com.javaee.project.project3.form.FieldName;
import com.javaee.project.project3.form.StoreForm;
import com.javaee.project.project3.model.Store;
import com.javaee.project.project3.service.CityService;
import com.javaee.project.project3.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "store")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private CityService cityService;

    @GetMapping(value = "all")
    public String getAllByGivenList(Model model, List<Store> stores){
        if(stores.isEmpty())
            stores=storeService.getAll();
        model.addAttribute("stores",stores);
        model.addAttribute("cities",cityService.getAll());
        model.addAttribute("store",new StoreForm());

        return "store/store";
    }

    @GetMapping
    public String getAll(Model model){
        return getAllByGivenList(model,storeService.getAll());
    }

    @GetMapping("city/{cityId}")
    public String getAllByCity(@PathVariable Long cityId, Model model){
        return getAllByGivenList(model,storeService.getAllByCity(cityId));
    }
    @PostMapping("create")
    public String create(@ModelAttribute StoreForm storeForm,Model model){
        storeService.createByStoreForm(storeForm);
        return getAll(model);
    }

    @GetMapping(value = "delete/{id}")
    public String delete(@PathVariable(name = "id") Long id, Model model){
        storeService.deleteById(id);
        return getAll(model);
    }

    @GetMapping(value = "edit/{id}")
    public String edit(@PathVariable(name = "id")Long id, Model model){
        StoreForm storeForm=new StoreForm();
        model.addAttribute("storeId", id);
        model.addAttribute("store",storeForm);
        model.addAttribute("cities",cityService.getAll());
        return "store/edit";
    }

    @PostMapping(value = "edit/{id}")
    public String save(@PathVariable(name = "id") Long id, @ModelAttribute StoreForm storeForm, Model model){

        storeService.update(id,storeForm);
        return getAll(model);
    }


}
