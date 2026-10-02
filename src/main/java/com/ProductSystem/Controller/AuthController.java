package com.ProductSystem.Controller;

import com.ProductSystem.DTO.*;
import com.ProductSystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResp> signup(@Valid @RequestBody SignupReq signupReq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signupReq));
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResp>login(@Valid @RequestBody LoginReq loginReq){
        return ResponseEntity.ok().body(authService.login(loginReq));

    }
}
