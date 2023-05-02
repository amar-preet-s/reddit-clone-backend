package com.example.redit.validator;

import com.example.redit.dos.UserDO;

import java.util.regex.Pattern;

public class Validator {

    public static String validateUser(UserDO userDO) throws Exception{
        if(userDO == null){
            throw new Exception("register body not found");
        }

        if(!validateEmail(userDO.getMail())){
            throw new Exception("Email not Valid");
        }
        if(!validateUserName(userDO.getUsername())){
            throw new Exception("user not valid");
        }
        if(!validatePassword(userDO.getPassword())){
            throw new Exception("password not good");
        }
        return "Successful";
    }

    public static boolean validateEmail(String mail){
        if (mail == null || mail.equals(""))
            return false;
        String REGEX = "^[a-zA-Z0-9+_.-]+@[a-z]+\\.[a-z]{2,3}";
        return patternMatches(mail, REGEX);
    }

    public static boolean validateUserName(String username){
        if(username == null || username.equals(""))
            return false;
        return true;
    }

    public static boolean validatePassword(String password){
        if(password == null || password.equals("") || password.length()<5){
            return false;
        }
        return true;
    }

    public static boolean patternMatches(String string, String regex) {
        return Pattern.compile(regex).matcher(string).matches();
    }
}
