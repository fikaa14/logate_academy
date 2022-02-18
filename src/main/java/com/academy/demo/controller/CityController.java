package com.academy.demo.controller;

import com.academy.demo.dto.CityDTO;
import com.academy.demo.entity.City;
import com.academy.demo.exception.CustomSQLException;
import com.academy.demo.exception.ValidationException;
import com.academy.demo.search.CitySearch;
import com.academy.demo.service.CityService;
import com.academy.demo.specs.CitySearchSpecification;
import com.academy.demo.validator.CityCreateValidator;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/city")
public class CityController {

    private final CityService cityService;
    private final CityCreateValidator cityCreateValidator;

    @GetMapping(value = "search")
    public ResponseEntity<List<CityDTO>> getCities(CitySearch citySearch) {

        CitySearchSpecification citySearchSpecification = new CitySearchSpecification(citySearch);

        List<CityDTO> cities = cityService.getCities(citySearchSpecification);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CityDTO cityDTO) throws CustomSQLException, ValidationException {

        Errors errors = new BeanPropertyBindingResult(cityDTO, "cityDTO");
        // Ako koristimo mapu onda koristimo klasu MapPropertyBindingResult
        ValidationUtils.invokeValidator(cityCreateValidator, cityDTO, errors);

        if(errors.hasErrors())
        {
            throw new ValidationException(
                    "Validation error has been occured",
                    errors
                    );
        }

        cityService.save(cityDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> findById(@PathVariable Integer id)
    {
        City city = cityService.findById(id);
        return new ResponseEntity<>(city, HttpStatus.OK);
    }

}
