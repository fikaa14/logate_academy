package com.academy.demo.repository;

import com.academy.demo.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>
{
    //JPQL(jezik za pisanje OO upita)

    @Query(
            value = "SELECT role FROM Role as role WHERE role.name = :roleName"
    )
    List<Role> findByNameWithJPQL(@Param("roleName") String name);

    @Query(value = "select role.name from Role as role")
    List<String> findAllNames();



    List<Role> findByName(String name);

    List<Role> findByNameStartingWith(String namePart);

    List<Role> findByNameEndingWith(String namePart);

    List<Role> findByNameContaining(String namePart);

    List<Role> findByNameStartingWithAndDescriptionEndingWith(String namePart, String descriptionPart);

}
