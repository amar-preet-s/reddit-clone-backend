package com.example.redit.controller;

import com.example.redit.dos.UserDO;
import com.example.redit.service.AuthService;
import com.example.redit.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDO userDO){
        ResponseEntity<?> res;
        try{
            String message = Validator.validateUser(userDO);
            CompletableFuture<ResponseEntity<?>> cf = new CompletableFuture<>();
            authService.signup(userDO,cf);
            res = cf.get();
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
        return res;
    }
}
