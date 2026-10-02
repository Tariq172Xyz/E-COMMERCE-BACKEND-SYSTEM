package com.ProductSystem.Service;

import com.ProductSystem.DTO.*;

public interface AuthService {

    SignupResp signup(SignupReq signupReq);
    LoginResp login(LoginReq loginReq);
}
