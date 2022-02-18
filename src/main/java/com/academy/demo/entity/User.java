package com.academy.demo.entity;

import com.academy.demo.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="user")
@Getter
@Setter
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

    @Column(name="is_active")
    private Boolean isActive;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name= "user_role",
            joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    @PrimaryKeyJoinColumn
    private UserDetail userDetail;

    public User(UserDTO userDTO)
    {
        this.setFirstName(userDTO.getFirstName());
        this.setLastName(userDTO.getLastName());
        this.setUsename(userDTO.getUsername());
        this.setPassword(userDTO.getPassword());
        this.setCreatedAt(userDTO.getCreatedAt());
        this.setRoles(userDTO.getRoles());
        this.setUserDetail(userDTO.getUserDetail());
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
