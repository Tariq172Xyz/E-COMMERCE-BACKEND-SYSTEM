package com.ProductSystem.DTO;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
//                  user requesting into system
public class UserReq {

    @NotBlank(message = "Name is required")
    private String userName;
    @NotBlank(message = "Password is required")
    @Size(min = 6,message = "Password must be at-least 6 characters")
    private String password;
    @NotNull
    private String phoneNumber;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email input")
    private String email;


}

