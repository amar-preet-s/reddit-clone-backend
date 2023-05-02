package com.example.redit.dos;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDO {
    private String mail;
    private String username;
    private String password;
}
