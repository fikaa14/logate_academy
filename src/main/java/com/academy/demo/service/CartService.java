package com.academy.demo.service;

import com.academy.demo.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CartService {

    @Autowired
    @Qualifier("secureRestTemplate")
    private RestTemplate secureRestTemplate;

    public CartDTO findOneById(int id){

        String url = "http://192.168.21.105:8080/api/cart/" + id;

        try {
            ResponseEntity<CartDTO> responseEntity = secureRestTemplate.exchange(url, HttpMethod.GET, null, CartDTO.class);
            return responseEntity.getBody();
        }

        catch (Exception e) {
            return null;
        }


    }

}
