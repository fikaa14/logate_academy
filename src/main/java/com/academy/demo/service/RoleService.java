package com.academy.demo.service;

import com.academy.demo.dto.RoleDTO;
import com.academy.demo.entity.Role;
import com.academy.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void add(RoleDTO roleDTO)
    {
        Role role = new Role(roleDTO);
        roleRepository.save(role);
    }

    public void update(Integer id, RoleDTO roleDTO) {

        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent())
        {
            Role role = roleOptional.get();
            role.setName(roleDTO.getName());
            role.setDescription(roleDTO.getDescription());

            roleRepository.save(role);
        }
        else {
            throw new IllegalArgumentException("NEMA ROLE");
        }
    }
}
