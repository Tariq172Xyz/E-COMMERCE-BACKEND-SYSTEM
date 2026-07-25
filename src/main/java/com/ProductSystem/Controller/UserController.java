package com.ProductSystem.Controller;

import com.ProductSystem.DTO.UserReq;
import com.ProductSystem.DTO.UserResp;
import com.ProductSystem.DTO.UserUpdateReq;
import com.ProductSystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResp>registerUser(@Valid @RequestBody UserReq userReq){
        return ResponseEntity.status(201).body(userService.addUser(userReq));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResp>getUserById(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserResp>>getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String>deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted Successfully");
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResp>updateUser(@PathVariable Long userId ,@RequestBody UserUpdateReq userUpdateReq){
        return ResponseEntity.ok(userService.updateUser(userId,userUpdateReq));
    }




}
