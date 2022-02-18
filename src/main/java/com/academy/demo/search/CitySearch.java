package com.academy.demo.search;

import com.academy.demo.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CitySearch {

    private String name;
    private String countryName;
    private String countryShortCode;

}
