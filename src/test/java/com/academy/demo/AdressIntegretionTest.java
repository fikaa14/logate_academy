package com.academy.demo;

import com.academy.demo.entity.Adress;
import com.academy.demo.entity.City;
import com.academy.demo.repository.AdressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest(classes = DemoAcademyApplication.class)
public class AdressIntegretionTest {

    @Autowired
    private AdressRepository adressRepository;

    @Test
    public void findByShortCode()
    {
        List<Adress> adresses = adressRepository.findByPostalCode("81000");
        log.info("Adress: {}", adresses.get(0));
    }

    @Test
    public void findByCity()
    {
        List<Adress> adresses = adressRepository.findByCityName("Podgorica");
        log.info("Adress: {}", adresses.get(0));
    }

    @Test
    public void findByCountryShortCode()
    {
        List<Adress>adresses = adressRepository.findByCityCountryShortCode("RU");
        log.info("Adress: {}", adresses);
    }

    @Test
    public void findByShortCodeUsingJPQL()
    {
        List<Adress> adresses = adressRepository.findByPostalCodeJPQL("81000");
        log.info("Adress: {}", adresses.get(0));
    }

    @Test
    public void findByCityNameUsingJPQL()
    {
        List<Adress> adresses = adressRepository.findByCityNameJPQL("Podgorica");
        log.info("Adress: {}", adresses);
    }

    @Test
    public void findByCountryShortCodeJPQL()
    {
        List<Adress>adresses = adressRepository.findByCityCountryShortCodeJPQL("ME");
        log.info("Adress: {}", adresses);
    }

    @Test
    public void findById()
    {
        Optional<Adress> adressOptional = adressRepository.findById(1);

        if(adressOptional.isPresent())
        {
            Adress adress = adressOptional.get();
        }
    }
}
