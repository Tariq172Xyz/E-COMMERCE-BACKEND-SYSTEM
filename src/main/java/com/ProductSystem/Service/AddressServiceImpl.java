package com.ProductSystem.Service;

import com.ProductSystem.DTO.AddressReq;
import com.ProductSystem.DTO.AddressResp;
import com.ProductSystem.Entity.Address;
import com.ProductSystem.Entity.User;
import com.ProductSystem.Exceptions.AddressNotFoundException;
import com.ProductSystem.Exceptions.UserNotFoundException;
import com.ProductSystem.Repository.AddressRepository;
import com.ProductSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public AddressResp addAddress(AddressReq addressReq) {

        User user = userRepository.findById(addressReq.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Address address = new Address();
        address.setCity(addressReq.getCity());
        address.setProvince(addressReq.getProvince());
        address.setCountry(addressReq.getCountry());
        address.setPostalCode(addressReq.getPostalCode());
        address.setUser(user);

        Address newAddress = addressRepository.save(address);

        return modelMapper.map(newAddress, AddressResp.class);
    }


    @Override
    public List<AddressResp> getAddressesByUser(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found");
        }
        return addressRepository.findByUserId(userId)
                .stream()
                .map(address -> modelMapper.map(address, AddressResp.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddressResp updateAddress(Long addressId, AddressReq addressReq) {
        Address address=addressRepository.findById(addressId)
                .orElseThrow(()->new AddressNotFoundException("Address not found"));

        address.setCity(addressReq.getCity());
        address.setProvince(addressReq.getProvince());
        address.setCountry(addressReq.getCountry());
        address.setPostalCode(addressReq.getPostalCode());

        Address updatedAddress=addressRepository.save(address);

        return modelMapper.map(updatedAddress,AddressResp.class);
    }

    @Override
    public void deleteAddress(Long addressId) {
       Address address= addressRepository.findById(addressId)
               .orElseThrow(()-> new AddressNotFoundException("Address not found"));

       addressRepository.delete(address);
    }

    @Override
    public AddressResp getAddressById(Long addressId) {
        Address address= addressRepository.findById(addressId)
                .orElseThrow(()-> new AddressNotFoundException("Address not found"));

        return modelMapper.map(address,AddressResp.class);
    }
}
