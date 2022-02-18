package com.academy.demo.validator;

import com.academy.demo.dto.CityDTO;
import com.academy.demo.entity.Country;
import com.academy.demo.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CityCreateValidator implements Validator {

    private final CountryRepository countryRepository;

    @Override
    public boolean supports(Class<?> clazz)
    {
        return clazz.isAssignableFrom(CityDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors)
    {
        CityDTO cityDTO = (CityDTO) target;

        validateCityName(cityDTO.getName(), errors);
        validateCountry(cityDTO.getCountry(), errors);
    }

    private void validateCountry(Country country, Errors errors)
    {
        if(country==null)
        {
            errors.rejectValue("country", "country.required", "Country is required");
        }

        if(country.getId() == null)
        {
            errors.rejectValue(
                    "country.id",
                    "country.id.required",
                    "Country identifeir is required"
            );
            return;
        }

        boolean countryExists = countryRepository.existsById(country.getId());
        if(!countryExists)
        {
            errors.rejectValue(
                    "country.id",
                    "country.id.invalid",
                    "Country identifeir does not exists"
            );
        }

    }

    private void validateCityName(String name, Errors errors)
    {
        if(name==null)
        {
            errors.rejectValue("name", "name.required", "Name is required");
            return;
        }
        if(name.trim().equals(""))
        {
            errors.rejectValue("name", "name.empty", "Name is empty");
            return;
        }
        if(name.length() > 128)
        {
            errors.rejectValue("name", "name.length-exceeded", "Name legth is exceeded");
        }
    }
}
