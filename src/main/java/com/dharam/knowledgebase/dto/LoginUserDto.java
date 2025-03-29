package com.dharam.knowledgebase.dto;

import lombok.Data;

@Data
public class LoginUserDto {
    String userName;
    String password;

    public LoginUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
