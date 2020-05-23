package com.javaee.project.project3.controller;

import com.javaee.project.project3.form.FieldName;
import com.javaee.project.project3.form.ProductForm;
import com.javaee.project.project3.model.Price;
import com.javaee.project.project3.model.Product;
import com.javaee.project.project3.model.ProductCategory;
import com.javaee.project.project3.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService categoryService;
    @Autowired
    private PriceService priceService;
    @Autowired
    private StoreService storeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ImageService imageService;

    @GetMapping()
    public String getAllProducts(@RequestParam(name = "field",defaultValue = "") String field,Model model) {
        System.out.println(field);
        return getProducts(model, productService.getAllBySearchText(field));
    }

    @GetMapping(value = "all")
    public String getProducts(Model model, List<Product> productsList) {
        model.addAttribute("products", productsList);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("categoryId", 0L);
        model.addAttribute("cityId", 0L);
        if (!model.containsAttribute("isBy"))
            model.addAttribute("isBy", false);
        if (!model.containsAttribute("categoryName"))
            model.addAttribute("categoryName", "");
        model.addAttribute("searchText", new FieldName());
        return "products";
    }

    @GetMapping(value = "byCategory/{categoryId}")
    public String getProductsByCategory(@PathVariable(name = "categoryId") Long categoryId, Model model) {
        List<Product> products = productService.getAllByCategory(categoryId);
        ProductCategory category = categoryService.getCategoryById(categoryId);
        model.addAttribute("isBy", true);
        model.addAttribute("categoryName", category.getName());
        return getProducts(model, products);
    }

    @GetMapping(value = "search")
    public String getProductsBySearch(@RequestParam(name = "field") String search, Model model) {
        List<Product> products = productService.getAllBySearchText(search);
        model.addAttribute("isBy", true);
        System.out.println(search);
        for (Product p : products) {
            System.out.println(p.getName());
        }
        ;
        model.addAttribute("categoryName", "Search Results");
        return getProducts(model, products);
    }

    @GetMapping("{id}")
    public String getById(@PathVariable Long id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        List<Price> prices = priceService.getPricesByProduct(product.getId());
        model.addAttribute("prices", prices);
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("stores", storeService.getAll());
        return "Product";
    }

    @PostMapping("create")
    public String createProductWithPrice(@ModelAttribute ProductForm productForm) {
        productService.saveFromForm(productForm);
        return "redirect:";
    }

    @PostMapping("edit/{id}")
    public String editProductWithPrice(@PathVariable Long id, @ModelAttribute ProductForm productForm) {
        productService.updateFromForm(id,productForm);
        return "redirect:/products/"+id;
    }

    @GetMapping("create")
    public String createPage(Model model) {
        model.addAttribute("productForm", new ProductForm());
        model.addAttribute("stores", storeService.getAll());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "productEdit";
    }

    @GetMapping("products/buy/{priceId}")
    public String orderProduct(@PathVariable(name = "priceId") Long priceId){
        orderService.create(priceId);
        return "redirect:/products";
    }

    @PostMapping("upload/{id}")
    public String uploadFile(@PathVariable Long id,@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {
        String fileName=imageService.uploadImageGetPath(file);
        productService.setImagePath(fileName,id);
        return "redirect:/products/"+id;
    }

    @RequestMapping(value = "image/big/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable("id") Long id){
        return imageService.getImageById(productService.getById(id));
    }


}
