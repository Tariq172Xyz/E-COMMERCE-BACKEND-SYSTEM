package com.ProductSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupResp {
    private Long userId;
    private String userName;
    private String email;
    private String phoneNumber;

}
