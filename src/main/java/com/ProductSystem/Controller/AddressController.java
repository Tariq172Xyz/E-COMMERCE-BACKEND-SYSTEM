package com.ProductSystem.Controller;

import com.ProductSystem.DTO.AddressReq;
import com.ProductSystem.DTO.AddressResp;
import com.ProductSystem.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResp> addAddress(
            @Valid @RequestBody AddressReq addressReq) {
        return ResponseEntity.status(201)
                .body(addressService.addAddress(addressReq));
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<AddressResp> getAddressById(
            @PathVariable Long addressId) {
        return ResponseEntity.ok(
                addressService.getAddressById(addressId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressResp>> getAddressesByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(
                addressService.getAddressesByUser(userId));
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResp> updateAddress(@PathVariable Long addressId, @Valid @RequestBody AddressReq addressReq) {
        return ResponseEntity.ok(
                addressService.updateAddress(addressId, addressReq));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddress(
            @PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.ok("Address deleted successfully");
    }
}