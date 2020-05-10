package com.javaee.project.project3.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class ProductForm {

    private Long id;

    @NotBlank
    private String name;

    @Size(min = 10,max = 500)
    @NotBlank
    private String description;

    @NotBlank
    private Long categoryId;


    private String bigImagePath;

    private String smallImagePath;

    private Long storeId;

    private Double priceDouble;
}
