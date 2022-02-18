package com.academy.demo.dto;

import com.academy.demo.entity.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDTO {

    private Integer id;
    private String name;
    private Country country;

}
