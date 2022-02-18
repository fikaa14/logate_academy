package com.academy.demo.controller;

import com.academy.demo.dto.RoleDTO;
import com.academy.demo.dto.UserDTO;
import com.academy.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("@userAuth.isAuthorized(#authKey)")
    public ResponseEntity<Void> add(
            @RequestBody UserDTO userDTO, @RequestHeader(value = "Authorization") String authKey)
    {
        userService.add(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@userAuth.isAuthorized(#authKey)")
    public ResponseEntity<Void> addRole(@PathVariable Integer id,
                                        @RequestBody RoleDTO roleDTO,
                                        @RequestHeader(value = "Authorization") String authKey)
    {
        userService.addRole(id, roleDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
