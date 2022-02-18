package com.academy.demo.service;

import com.academy.demo.dto.CountryDTO;
import com.academy.demo.entity.Country;
import com.academy.demo.mapper.CountryMapper;
import com.academy.demo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CountryMapper countryMapper;

    public Page<Country> findPage(Pageable pageable)
    {
        return countryRepository.findAllUsingJPQL(pageable);
    }

    public List<Country> findAllPageable(Pageable pageable)
    {
        Page<Integer> countryIdsPage = countryRepository.findIdsPageable(pageable);

        List<Integer> countryIds = countryIdsPage.getContent();

        return countryRepository.findByIdin(countryIds);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void save(CountryDTO countryDTO)
    {
        countryDTO.setId(null);
        Country country = countryMapper.convertToEntity(countryDTO);
        //countryRepository.save(new Country(countryDTO));
        countryRepository.save(country);

        throw new IllegalArgumentException("Country error");
    }

    public void update(Integer id, CountryDTO countryDTO)
    {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if(countryOptional.isPresent())
        {
            Country country = countryOptional.get();

            country.setName(countryDTO.getName());
            country.setShortCode(countryDTO.getShortCode());

            countryRepository.save(country);
        } else
            throw new IllegalArgumentException("Country not exists!");
    }

    public void delete(Integer id)
    {
        countryRepository.deleteById(id);
    }

    public void deleteWithEntity(Integer id)
    {
        Optional<Country> countryOptional = countryRepository.findById(id);
        if(countryOptional.isPresent())
        {
            Country country = countryOptional.get();
            countryRepository.delete(country);
        }
    }
}
