package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.City;
import com.javaee.project.project3.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepo extends JpaRepository<Store,Long> {
    @Query("select st from Store st where st.city.id=:cityId")
    List<Store> getAllByCity(Long cityId);
}
