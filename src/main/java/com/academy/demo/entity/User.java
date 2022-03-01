package com.academy.demo.entity;

import com.academy.demo.dto.UserDTO;
import com.academy.demo.security.dto.UserForRegistrationDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column
    private String usename; //napravio sam gresku u bazi

    @Column
    private String password;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name="created_at")
    private Date createdAt;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(
            name= "user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST,  fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @JsonIgnore
    private UserDetail userDetail;

    public User(UserDTO userDTO)
    {
        this.setFirstName(userDTO.getFirstName());
        this.setLastName(userDTO.getLastName());
        this.setUsename(userDTO.getUsername());
        this.setPassword(userDTO.getPassword());
        this.setCreatedAt(userDTO.getCreatedAt());
        this.setRoles(userDTO.getRoles());
    }

    public User(UserForRegistrationDTO userForRegistrationDTO)
    {
        this.setFirstName(userForRegistrationDTO.getFirstName());
        this.setLastName(userForRegistrationDTO.getLastName());
        this.setUsename(userForRegistrationDTO.getUsername());
        this.setPassword(userForRegistrationDTO.getPassword());
    }

    public void addRole(Role role)
    {
        this.getRoles().add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", usename='" + usename + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public void removeRole(Role role)
    {
        this.getRoles().remove(role);
    }

    public void removeById(int roleId)
    {
        Set<Role> roles = this.getRoles();
        for(Role role : roles)
        {
            if (role.getId().equals(roleId))
            {
                roles.remove(role);
                break;
            }
        }
    }
}
