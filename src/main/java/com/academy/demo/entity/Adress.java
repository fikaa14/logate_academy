package com.academy.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="adress")
@Getter
@Setter
@ToString
public class Adress {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="city_id")
    @ToString.Exclude
    private City city;

    @Column
    private String street;

    @Column
    private String number;

    @Column(name="postal_code")
    private String postalCode;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable (
            name = "customer_adress",
            joinColumns = @JoinColumn(name = "adress_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="customer_id", referencedColumnName = "id")
    )
    @JsonIgnore
    @ToString.Exclude
    private Set<Customer> customers = new HashSet<>();

}
