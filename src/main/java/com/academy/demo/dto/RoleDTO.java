package com.academy.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RoleDTO {

    private Integer id;
    private String name;
    private String description;

    public RoleDTO(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

}
