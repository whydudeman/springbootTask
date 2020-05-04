package com.javaee.project.dto;

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
