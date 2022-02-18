package com.academy.demo.dto;

import com.academy.demo.entity.Adress;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class CustomerWithAdressDTO {

    private Integer id;
    private String name;
    private String phoneNumber;
    private Set<Adress> addresses = new HashSet<>();

    public CustomerWithAdressDTO(Integer id, String name, String phoneNumber, Set<Adress> addresses) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.addresses = addresses;
    }

}
