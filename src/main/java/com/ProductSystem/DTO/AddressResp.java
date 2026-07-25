package com.ProductSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResp {


    private String city;
    private String province;
    private String postalCode;
    private String country;
    private Long userId;
}
