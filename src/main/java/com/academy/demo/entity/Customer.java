package com.academy.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="full_name")
    private String fullName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(name="registred_at")
    private Date registredAt;

    @ManyToMany(mappedBy = "customers")
    @JsonIgnore
    private Set<Adress> adresses = new HashSet<>();

}
