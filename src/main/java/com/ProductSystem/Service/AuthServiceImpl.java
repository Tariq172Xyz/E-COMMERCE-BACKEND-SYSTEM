package com.ProductSystem.Service;

import com.ProductSystem.DTO.*;
import com.ProductSystem.Entity.User;
import com.ProductSystem.Exceptions.InvalidCredentialsException;
import com.ProductSystem.Exceptions.InvalidInputException;
import com.ProductSystem.Exceptions.UserAlreadyExistsException;
import com.ProductSystem.Repository.UserRepository;
import com.ProductSystem.Security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;



    @Override
    public SignupResp signup(SignupReq signupReq) {
        if (userRepository.existsByEmail(signupReq.getEmail())){
            throw new UserAlreadyExistsException("User with this ID already exists");
        }
        User user= modelMapper.map(signupReq,User.class);
        user.setPassword(passwordEncoder.encode(signupReq.getPassword()));

        userRepository.save(user);
        return modelMapper.map(user, SignupResp.class);

    }

//    @Override
//    public LoginResp login(LoginReq loginReq) {
//
//        Authentication auth=authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginReq.getEmail(),loginReq.getPassword())
//        );
//
//        UserDetails userDetails=(UserDetails) auth.getPrincipal();
//        String token =jwtUtil.generateToken(userDetails);
//
//        return new LoginResp(token);
//    }

    @Override
    public LoginResp login(LoginReq loginReq) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getEmail(), loginReq.getPassword())
            );

            UserDetails userDetails = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);

            return new LoginResp(token);
        }
        catch (BadCredentialsException e){
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }


}
