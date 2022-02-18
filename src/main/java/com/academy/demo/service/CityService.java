package com.academy.demo.service;

import com.academy.demo.dto.CityDTO;
import com.academy.demo.dto.CountryDTO;
import com.academy.demo.entity.City;
import com.academy.demo.exception.CustomSQLException;
import com.academy.demo.mapper.CityMapper;
import com.academy.demo.mapper.CountryMapper;
import com.academy.demo.repository.CityRepository;
import com.academy.demo.specs.CitySearchSpecification;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
@Transactional(readOnly = true)
public class CityService {

    private CityRepository cityRepository;
    private CityMapper cityMapper;
    private CountryService countryService;
    private CountryMapper countryMapper;

    public List<CityDTO> getCities(CitySearchSpecification citySearchSpecification) {
        List<City> cityList = cityRepository.findAll(citySearchSpecification);
        List<CityDTO> cities = new ArrayList<>();

        for (City city : cityList) {
            CityDTO cityDTO = cityMapper.convertToDTO(city);
            cities.add(cityDTO);
        }

        return cities;

    }

    @Cacheable(cacheNames = "city", key = "#id")
    public City findById(Integer id)
    {
        City city = cityRepository.findById(id).orElse(null);
        return city;
    }

    //@SneakyThrows
    @Transactional(rollbackFor = CustomSQLException.class)
    public void save(CityDTO cityDTO) throws CustomSQLException
    {
        log.info("Saving new city");
        City city = cityMapper.convertToCity(cityDTO);

        cityRepository.save(city);

        //throw new CustomSQLException("Error occured - manual!");
        CountryDTO countryDTO = countryMapper.convertToDTO(city.getCountry());
        try{
            countryService.save(countryDTO);
        } catch (IllegalArgumentException e)
        {
            log.error("Country not saves!");
        }


    }
}
