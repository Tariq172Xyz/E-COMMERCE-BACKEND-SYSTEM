package com.ProductSystem.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupReq {
    @NotBlank
    private String userName;
    @NotBlank @Size(min = 6 , max = 20)
    private String password;
    @NotNull
    private String phoneNumber;
    @NotBlank @Email
    private String email;

}
