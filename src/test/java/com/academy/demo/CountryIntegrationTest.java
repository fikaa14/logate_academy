package com.academy.demo;

import com.academy.demo.dto.CountryDTO;
import com.academy.demo.entity.Country;
import com.academy.demo.projection.CountryIdAndShortCodeProjection;
import com.academy.demo.repository.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.List;

@Slf4j
@SpringBootTest(classes = DemoAcademyApplication.class)
public class CountryIntegrationTest {

    @Autowired
    public CountryRepository countryRepository;

    @Test
    public void insertChina()
    {
        Country country = new Country();
        country.setName("China");
        country.setShortCode("CH");

        countryRepository.save(country);
    }

    @Test
    public void insertIndia()
    {
        Country country = new Country();
        country.setName("India");
        country.setShortCode("IN");

        countryRepository.save(country);
    }

    @Test
    public void findIdAndShortCodeWithConstructorMappingTest()
    {
        List<CountryDTO> countries = countryRepository.findIdAndShortCode();
        log.info("Countries: {}", countries);
    }

    @Test
    public void findNameAndShortCodeWithConstructorMappingTest()
    {
        List<CountryDTO> countries = countryRepository.findNameAndShortCode();
        log.info("Countries: {}", countries);
    }

    @Test
    public void findIdAndShortCodeUsingTuple()
    {
        List<Tuple> countries = countryRepository.findIdAndShortCodeUsingTuple();
        for(Tuple tuple:countries)
        {
            log.info("Id:{} | Short Code: {}", tuple.get("id"), tuple.get("shortCode"));

            Integer id = (Integer) tuple.get("id");
            String shortCode = (String) tuple.get("shortCode");
        }
    }

    @Test
    public void findIdAndShortCodeUsingCustomProjectionTest()
    {
        List<CountryIdAndShortCodeProjection> countries = countryRepository.findIdAndShortCodeUsingCustomProjection();
        for(CountryIdAndShortCodeProjection projection : countries)
        {
            Integer id = projection.getId();
            String shortCode = projection.getShortCode();

            log.info("Id: {} | Short Code: {}", id, shortCode);
        }
    }

    @Test
    public void findIdAndShortCodeUsingNativeQueryTest()
    {
        List<CountryIdAndShortCodeProjection> countries = countryRepository.findIdAndShortCodeUsingNativeQuery();
        for(CountryIdAndShortCodeProjection projection : countries)
        {
            Integer id = projection.getId();
            String shortCode = projection.getShortCode();

            log.info("Id: {} | Short Code: {}", id, shortCode);
        }
    }

    @Test
    public void findAllWithPageableTest()
    {
        Pageable pageable = PageRequest.of(0, 2);

        Page<Country> countryPage = countryRepository.findAll(pageable);
        log.info("Country page: {}", countryPage);

        if(countryPage.hasContent())
        {
            List<Country> countries = countryPage.getContent();
            long totalElemnts = countryPage.getTotalElements();
            int totalPages = countryPage.getTotalPages();
        }
    }
}
