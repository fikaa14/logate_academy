package com.academy.demo.security.dto;

import com.academy.demo.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserForRegistrationDTO {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> roles;

}
