package com.academy.demo;

import com.academy.demo.entity.City;
import com.academy.demo.entity.Country;
import com.academy.demo.repository.CityRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest
public class CityIntegrationTest {

    @Autowired
    private CityRepository cityRepository;

    @Test
    public void getAllCitiesFromGivenCountryTest()
    {
        List<City> cities = cityRepository.findAllCitiesFromGivenCountry("ME");
        log.info("Cities:{} ", cities);
    }

    @Test
    @Transactional
    public void getOneCityByIdTest(){
        Optional<City> cityOptional = cityRepository.findById(2);
        if(cityOptional.isPresent())
        {
            City city = cityOptional.get();
            Country country = city.getCountry();
            log.info("Country: {}", country);
        }
    }

    @Test
    @Transactional
    public void getAllCities()
    {
        List<City> cities= cityRepository.findAllWithCountries();
        for(City city:cities)
        {
            city.getCountry();
        }
    }

}
