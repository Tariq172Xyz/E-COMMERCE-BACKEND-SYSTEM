package com.ProductSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginReq {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Size(max = 15 , min = 6)
    private String password;
}
