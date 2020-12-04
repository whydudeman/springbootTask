package com.javaee.project.project3.service;

import com.javaee.project.project1.model.Category;
import com.javaee.project.project1.service.CategoryService;
import com.javaee.project.project3.form.ProductForm;
import com.javaee.project.project3.model.Price;
import com.javaee.project.project3.model.Product;
import com.javaee.project.project3.model.Store;
import com.javaee.project.project3.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private PriceService priceService;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public List<Product> getAllByCategory(Long id) {
        return productRepo.getAllByCategoryId(id);
    }

    public List<Product> getAllBySearchText(String search) {
         List<Product> products=productRepo.getAllByNameLike(search);
         for (Product p : products){
             System.out.println(p.getName()+" namee");
         }
        return products;
    }

    public Product getById(Long id) {
       return productRepo.findById(id).orElseThrow(()->new EntityExistsException("NO_PRODUCT_WITH_THIS_ID"));
    }

    public void saveFromForm(ProductForm productForm) {
        Product product=new Product();
        product.setDescription(productForm.getDescription());
        product.setName(productForm.getName());
        product.setCategory(categoryService.getCategoryById(productForm.getCategoryId()));
        product=productRepo.save(product);
        Store store=storeService.getStoreById(productForm.getStoreId());
        priceService.createPrice(productForm.getPriceDouble(),product,store);
    }

    public void updateFromForm(Long id,ProductForm productForm) {
        Product product=getById(id);
        Store store=storeService.getStoreById(productForm.getStoreId());
        if(!priceService.isPriceExists(product.getId(),store.getId()))
            priceService.createPrice(productForm.getPriceDouble(),product,store);
        throw new EntityExistsException("This store already has price on this product");
    }

    public void setImagePath(String fileName, Long id) {
        Product product=getById(id);
        product.setBigImagePath(fileName);
        product.setSmallImagePath(fileName);
        productRepo.save(product);
    }
}
