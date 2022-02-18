package com.academy.demo.dto;

import com.academy.demo.entity.Role;
import com.academy.demo.entity.UserDetail;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@ToString
public class UserDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Date createdAt;
    @ToString.Exclude
    private Set<Role> roles;
    @ToString.Exclude
    private UserDetail userDetail;

}
