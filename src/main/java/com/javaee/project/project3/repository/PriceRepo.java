package com.javaee.project.project3.repository;

import com.javaee.project.project3.model.City;
import com.javaee.project.project3.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PriceRepo extends JpaRepository<Price,Long> {
    List<Price> getPriceByProductId(Long productId);

    @Query("select case when count(p) > 0  THEN true ELSE false End From Price p where p.product.id=:productId and p.store.id=:storeId")
    boolean isPriceExists(Long productId, Long storeId);
}
