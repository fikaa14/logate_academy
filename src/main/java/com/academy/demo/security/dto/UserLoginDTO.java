package com.academy.demo.security.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginDTO {

    private final String username;
    private final String password;

}