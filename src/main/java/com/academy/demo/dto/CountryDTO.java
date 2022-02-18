package com.academy.demo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryDTO {

    private Integer id;
    private String shortCode;
    private String name;

    public CountryDTO() {}

    public CountryDTO(Integer id, String shortCode) {
        this.id = id;
        this.shortCode = shortCode;
    }

    public CountryDTO(String shortCode, String name)
    {
        this.name = name;
        this.shortCode = shortCode;
    }
}
