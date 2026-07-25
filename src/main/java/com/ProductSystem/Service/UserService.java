package com.ProductSystem.Service;

import com.ProductSystem.DTO.UserReq;
import com.ProductSystem.DTO.UserResp;
import com.ProductSystem.DTO.UserUpdateReq;

import java.util.List;

public interface UserService {

    UserResp addUser(UserReq userReq);
    UserResp getUserById(Long userId);
    void deleteUser(Long userId);
    UserResp updateUser(Long userId, UserUpdateReq userUpdateReq);
    List<UserResp>getAllUsers();
}
