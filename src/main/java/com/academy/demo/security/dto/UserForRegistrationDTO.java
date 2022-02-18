package com.academy.demo.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserForRegistrationDTO {

    private final String username;
    private final String password;
    private final String firstName;
    private final String lastName;

}
