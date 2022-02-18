package com.academy.demo;

import com.academy.demo.entity.City;
import com.academy.demo.entity.Country;
import com.academy.demo.repository.CityRepository;
import com.academy.demo.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = DemoAcademyApplication.class)
public class CityIntegrationTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

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

    @Test
    @Transactional
    public void insertParisTest()
    {
        Country country = countryRepository.getById(1);
        //country.setName("France");
        //country.setShortCode("FR");

        City city = new City();
        city.setName("Paris");
        city.setCountry(country);

        cityRepository.save(city);
    }

}
