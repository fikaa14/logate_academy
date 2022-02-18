package com.academy.demo;

import com.academy.demo.entity.Role;
import com.academy.demo.entity.User;
import com.academy.demo.entity.UserDetail;
import com.academy.demo.repository.RoleRepository;
import com.academy.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@SpringBootTest(classes = DemoAcademyApplication.class)
public class UserIntegretionTest {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Test
    public void insertUserWithDetailsTest()
    {
        User user = new User();
        user.setUsename("usr-002");
        user.setPassword("usr-pass-002");
        user.setCreatedAt(new Date());
        user.setFirstName("Filip");
        user.setLastName("Maras");

        UserDetail userDetail = new UserDetail();
        userDetail.setPhoneNumber("11222333");
        userDetail.setAge(30);
        userDetail.setAdress("Adress-001");
        userDetail.setUser(user);

        user.setUserDetail(userDetail);

        userRepository.save(user);
    }

    @Test
    public void insertUserRolesWithParentEntityTest()
    {
        User user = new User();
        user.setUsename("usr-0012");
        user.setPassword("usr-pass-0012");
        user.setCreatedAt(new Date());
        user.setFirstName("Filip-001");
        user.setLastName("Maras-001");

        Role manager = new Role();
        manager.setName("Manager");
        manager.setDescription("Rola menadzer");

        Role developer = new Role();
        developer.setName("Developer");
        developer.setDescription("Role Developer");

        Set<Role> roles = new HashSet<>();
        roles.add(manager);
        roles.add(developer);

        user.setRoles(roles);

        userRepository.save(user);
    }

    //@Transactional
    @Test
    public void ivalidateUserRolesTest()
    {
        Optional<User> userOptional = userRepository.findById(2);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            //Set<Role> roles = user.getRoles();

            user.setRoles(null);
            userRepository.save(user);

            log.info("User: {}", user);
            //log.info("Roles: {}", roles);
        }
    }

    @Test
    public void addOneNewRoleToExistingUserTest()
    {
        Optional<User> userOptional = userRepository.findById(2);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            Role role = roleRepository.findById(2).orElse(null);

            Set<Role> roles = new HashSet<>();
            roles.add(role);

            user.setRoles(roles);

            userRepository.save(user);
        }
    }

    @Transactional
    @Test
    public void addAnotherRoleToExistingUserTest()
    {
        Optional<User> userOptional = userRepository.findById(2);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            Role role = roleRepository.findById(3).orElse(null);
            //Role role = roleRepository.getById(3);
            user.addRole(role);

            log.info("RoleID: {}", role.getName());
        }
    }

    @Test
    public void removeRoleFromExistingUserTest()
    {
        Optional<User> userOptional = userRepository.findById(2);
        if(userOptional.isPresent())
        {
            User user = userOptional.get();
            Role role = roleRepository.findById(3).orElse(null);

            //user.removeRole(role);
            user.removeById(3);
            userRepository.save(user);
        }
    }
}
