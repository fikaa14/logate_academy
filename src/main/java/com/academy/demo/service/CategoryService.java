package com.academy.demo.service;

import com.academy.demo.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("secureRestTemplate")
    private RestTemplate secureRestTemplate;

    public List<String> findAllExternal()
    {

        String url = "https://fakestoreapi.com/products/categories";

        try
        {
            ParameterizedTypeReference<List<String>> typeReference = new ParameterizedTypeReference<List<String>>() {};

            ResponseEntity<List<String>> responseEntity =
                    secureRestTemplate.exchange(url, HttpMethod.GET, null, typeReference);

            LOGGER.info("Response body : {}" , responseEntity.getBody());
            LOGGER.info("Response Http Stetus Code: {}", responseEntity.getStatusCodeValue());

            List<String> categories =  responseEntity.getBody();
            return categories;
        }
        catch (Exception e)
        {
            LOGGER.info("Greska");
            return new ArrayList<>();
            //return Collections.emptyList();
        }
    }

}
