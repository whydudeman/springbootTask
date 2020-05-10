package com.javaee.project.project3.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AuthForm {
    @NotBlank
    @Min(4)
    private String username;
    @NotBlank
    @Min(4)
    private String password;

    public AuthForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
