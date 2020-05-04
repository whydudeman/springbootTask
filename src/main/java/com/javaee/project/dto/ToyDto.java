package com.javaee.project.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ToyDto {
    private Long id;
    private String name;
    @NotNull(message = "Please enter id")
    private Double price;
    private Long categoryId;
}
