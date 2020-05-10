package com.javaee.project.project3.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationForm {
    @Size(min = 4, max = 50)
    private String username;
    @Size(min = 5, max = 50)
    private String password;
    @NotBlank
    @Email(message = "Please enter a valid e-mail address")
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @CreatedDate
    private LocalDateTime createdAt;

}
