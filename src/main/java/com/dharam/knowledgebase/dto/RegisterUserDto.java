package com.dharam.knowledgebase.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {
    private String userName;
    private String email;
    private String password;
    private Set<String> roles;

}
