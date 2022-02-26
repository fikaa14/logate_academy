package com.academy.demo.controller;

import com.academy.demo.dto.RoleDTO;
import com.academy.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody RoleDTO roleDTO)
    {
        roleService.add(roleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody RoleDTO roleDTO)
    {
        roleService.update(id, roleDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<RoleDTO>> all()
    {
        List<RoleDTO> roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
