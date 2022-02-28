package com.academy.demo.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserForRegistrationDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;

}
