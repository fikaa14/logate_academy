package com.academy.demo.service;

import com.academy.demo.dto.ProductDTO;
import com.academy.demo.dto.ProductFakeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    @Qualifier("secureRestTemplate")
    private RestTemplate secureRestTemplate;

    @Autowired
    private ProductFakerService fakerService;


    public List<ProductDTO> findAll() {
        return fakerService.findAll();
    }

    public Optional<ProductDTO> findOneByIdOptional(Integer productId) {
       return fakerService.findOneByIdOptional(productId);
    }

    public ProductDTO findOneById(Integer productId) {
        return fakerService.findOneById(productId);
    }

    public List<ProductDTO> findByCategory(String category)
    {
        return findAll()
            .stream()
            .filter(product -> product.getCategory().equals(category))
            .collect(Collectors.toList());
    }

    public void insert(ProductDTO product) {
        fakerService.insert(product);
    }

    public List<ProductFakeDTO> findAllFake(){

        String url = "https://fakestoreapi.com/products";

        try {
            ParameterizedTypeReference<List<ProductFakeDTO>> typeReference = new ParameterizedTypeReference<List<ProductFakeDTO>>() {};

            ResponseEntity<List<ProductFakeDTO>> responseEntity = secureRestTemplate.exchange(url, HttpMethod.GET, null, typeReference);

            return responseEntity.getBody();

        }
        catch (Exception e) {
            return Collections.emptyList();
        }

    }

}
