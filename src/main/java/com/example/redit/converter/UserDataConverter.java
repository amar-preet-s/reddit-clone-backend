package com.example.redit.converter;

import com.example.redit.dbos.UserDBO;
import com.example.redit.dos.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserDataConverter {

    @Autowired
    private PasswordEncoder passwordEncoder;
    public UserDBO convertDOtoDBO(UserDO user){
        if(user == null){
            return null;
        }

        UserDBO userDBO = new UserDBO();
        userDBO.setUsername(user.getUsername());
        userDBO.setEmail(user.getMail());
        userDBO.setPassword(passwordEncoder.encode(user.getPassword()));

        return userDBO;
    }


}
