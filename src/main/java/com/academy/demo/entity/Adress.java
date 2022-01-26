package com.academy.demo.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="adress")
@Getter
@Setter
public class Adress {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="city_id")
    private City city;

    @Column
    private String street;

    @Column
    private String number;

    @Column(name="postal_code")
    private String postalCode;


    @ManyToMany
    @JoinTable (
            name = "customer_adress",
            joinColumns = @JoinColumn(name = "adress_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="customer_id", referencedColumnName = "id")
    )
    private Set<Customer> customers = new HashSet<>();

}
