package com.academy.demo;

import com.academy.demo.entity.Role;
import com.academy.demo.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = DemoAcademyApplication.class)
public class RoleIntegretionTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void insert()
    {
        //INSERT INTO role(name, description) VALUES(?,?);

        Role role = new Role();
        role.setName("WEB DEV");
        role.setDescription("Description for WEB DEV");

        Role insertedRole = roleRepository.save(role);
    }

    @Test
    public void findAllTest()
    {
        //SELECT * FROM role;

        List<Role> roles = roleRepository.findAll();
        Role role = roles.get(0);
        log.info("id: {} | name: {} | description: {}", role.getId(), role.getName(), role.getDescription());
    }

    @Test
    public void findByIdTest()
    {
        //SELECT * FROM role WHERE id = ?;
        Optional<Role> roleOptional = roleRepository.findById(1);
        if(roleOptional.isPresent())
        {
            Role role = roleOptional.get();
            log.info("Role: {}", role);
        }
    }

    @Test
    public void deleteByIdTest()
    {
        //roleRepository.delete(role); //delete role
        roleRepository.deleteById(1); // delete role by id
    }

    @Test
    public void nameEndingwith()
    {
        List<Role> roles = roleRepository.findByNameEndingWith("DEV");
        log.info("Roles: {}", roles);
    }

}
