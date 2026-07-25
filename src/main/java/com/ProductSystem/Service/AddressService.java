package com.ProductSystem.Service;

import com.ProductSystem.DTO.AddressReq;
import com.ProductSystem.DTO.AddressResp;
import java.util.List;

public interface AddressService {
    AddressResp addAddress(AddressReq addressReq);
    List<AddressResp> getAddressesByUser(Long userId);
    AddressResp updateAddress(Long addressId, AddressReq addressReq);
    void deleteAddress(Long addressId);
    AddressResp getAddressById(Long addressId);
}
