package com.example.redit.service;

import com.example.redit.converter.UserDataConverter;
import com.example.redit.dbos.UserDBO;
import com.example.redit.dbos.VerificationTokenDBO;
import com.example.redit.dos.*;
import com.example.redit.repository.UserRepository;
import com.example.redit.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {

    @Autowired
    private UserDataConverter userDataConverter;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    public void signup(UserDO user, CompletableFuture<ResponseEntity<?>> cf){
        try{
            UserDBO userDBO = userDataConverter.convertDOtoDBO(user);
            userRepository.save(userDBO);

            String token = generateVerificationToken(userDBO);
            mailService.sendMail(new NotificationEmail("Please Activate your Account",
                    user.getMail(), "Thank you for signing up to Spring Reddit, " +
                    "please click on the below url to activate your account : " +
                    "http://localhost:8080/api/auth/accountVerification/" + token));

        cf.complete(new ResponseEntity<>("successfully added",HttpStatus.OK));
        }catch(Exception e){
            cf.complete(new ResponseEntity<>("Exception occured", HttpStatus.OK));
        }
    }

    private String generateVerificationToken(UserDBO user) {
        String token = UUID.randomUUID().toString();
        VerificationTokenDBO verificationTokenDBO = new VerificationTokenDBO();
        verificationTokenDBO.setToken(token);
        verificationTokenDBO.setUser(user);

        verificationTokenRepository.save(verificationTokenDBO);
        return token;
    }
}
