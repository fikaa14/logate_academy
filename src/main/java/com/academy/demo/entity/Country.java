package com.academy.demo.entity;

import com.academy.demo.dto.CountryDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
public class Country implements Serializable {

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

    public Country(CountryDTO countryDTO)
    {
        this.setId(countryDTO.getId());
        this.setName(countryDTO.getName());
        this.setShortCode(countryDTO.getShortCode());
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortCode='" + shortCode + '\'' +
                '}';
    }
}
