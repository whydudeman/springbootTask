package com.javaee.project.project3.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StoreForm {
    private Long id;
    private String name;
    private String address;
    private Long cityId;
}
