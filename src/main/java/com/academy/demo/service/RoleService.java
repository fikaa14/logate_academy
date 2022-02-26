package com.academy.demo.service;

import com.academy.demo.dto.RoleDTO;
import com.academy.demo.entity.Role;
import com.academy.demo.mapper.RoleMapper;
import com.academy.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

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

    public List<RoleDTO> getAll()
    {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleList = new ArrayList<>();

        for(Role role: roles)
        {
            roleList.add(roleMapper.toDTO(role));
        }

        return roleList;
    }
}
