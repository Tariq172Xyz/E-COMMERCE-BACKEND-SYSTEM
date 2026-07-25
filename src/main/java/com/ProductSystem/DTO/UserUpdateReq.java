package com.ProductSystem.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateReq {

    private String userName;
    private String password;
    private String phoneNumber;
    private String email;
}
