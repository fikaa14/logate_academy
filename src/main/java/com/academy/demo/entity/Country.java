package com.academy.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column(name = "short_code")
    private String shortCode;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<City> cities = new ArrayList<>();

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortCode='" + shortCode + '\'' +
                '}';
    }
}
