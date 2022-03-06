package com.academy.demo.service;

import com.academy.demo.dto.RoleDTO;
import com.academy.demo.dto.UserDTO;
import com.academy.demo.entity.Role;
import com.academy.demo.entity.User;
import com.academy.demo.mapper.UserMapper;
import com.academy.demo.repository.RoleRepository;
import com.academy.demo.repository.UserRepository;
import com.academy.demo.security.dto.UserForRegistrationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public void add(UserDTO userDTO)
    {
        User user = new User(userDTO);
       // user.getUserDetail().setUser(user);
        userRepository.save(user);
    }

    public void addRole(Integer id, RoleDTO roleDTO)
    {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            Role role = new Role(roleDTO);
            user.addRole(role);
            userRepository.save(user);
        }
    }

    public void getAutheticationUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
    }

    public void register(UserForRegistrationDTO userForRegistrationDTO)
    {
        String password = userForRegistrationDTO.getPassword();
        User user = new User(userForRegistrationDTO);

        for(String roleName:userForRegistrationDTO.getRoles())
        {
            roleName = "ROLE_".concat(roleName);
            Integer id = roleRepository.findIdByName(roleName);
            Optional<Role> roleOptional = roleRepository.findById(id);
            if(roleOptional.isPresent())
            {
                Role role = roleOptional.get();
                user.addRole(role);
            }
        }

        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public List<UserDTO> getAll()
    {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User user: users)
        {
            userDTOS.add(userMapper.toUserDTO(user));
        }
        return userDTOS;
    }

    public void deactivateById(Integer id)
    {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            user.setIsActive(false);

            userRepository.save(user);
        }

    }

    public boolean existsByUsername(String username)
    {
        return userRepository.existsByUsename(username);
    }
}
