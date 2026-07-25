package com.ProductSystem.Service;


import com.ProductSystem.DTO.UserReq;
import com.ProductSystem.DTO.UserResp;
import com.ProductSystem.DTO.UserUpdateReq;
import com.ProductSystem.Entity.User;
import com.ProductSystem.Exceptions.UserAlreadyExistsException;
import com.ProductSystem.Exceptions.UserNotFoundException;
import com.ProductSystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserResp addUser(UserReq userReq) {

        if (userRepository.existsByEmail(userReq.getEmail())){
            throw new UserAlreadyExistsException("User with this ID already exists");
        }
        User savedUser=modelMapper.map(userReq,User.class);
        savedUser.setPassword(passwordEncoder.encode(userReq.getPassword()));

       User newUser= userRepository.save(savedUser);

        return modelMapper.map(newUser,UserResp.class);
    }

    @Override
    public UserResp getUserById(Long userId) {
       User user=userRepository.findById(userId)
               .orElseThrow(()->new UserNotFoundException("User not found"));

       return modelMapper.map(user, UserResp.class);
    }

    @Override
    public void deleteUser(Long userId) {
      User user=  userRepository.findById(userId)
              .orElseThrow(()-> new UserNotFoundException("User with this ID does not exist"));

        userRepository.delete(user);
    }

    @Override
    public UserResp updateUser(Long userId, UserUpdateReq userUpdateReq) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // only update if value is NOT null
        if (userUpdateReq.getUserName() != null) {
            user.setUserName(userUpdateReq.getUserName());
        }
        if (userUpdateReq.getEmail() != null) {
            user.setEmail(userUpdateReq.getEmail());
        }
        if (userUpdateReq.getPhoneNumber() != null) {
            user.setPhoneNumber(userUpdateReq.getPhoneNumber());
        }
        if (userUpdateReq.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userUpdateReq.getPassword()));
        }
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserResp.class);
    }

    @Override
    public List<UserResp> getAllUsers() {

       return userRepository.findAll().stream()
               .map(user -> modelMapper.map(user,UserResp.class))
               .collect(Collectors.toList());
    }
}
