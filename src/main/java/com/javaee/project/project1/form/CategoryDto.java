package com.javaee.project.project1.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
