package com.academy.demo.controller;

import com.academy.demo.dto.CartDTO;
import com.academy.demo.dto.CartExtendsDTO;
import com.academy.demo.dto.ProductDTO;
import com.academy.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(value = "{id}")
    public ResponseEntity<CartDTO> findOneById(@PathVariable(value = "id") Integer cartID)
    {
        CartDTO cart = cartService.findOneById(cartID);
        return cart != null
                ? new ResponseEntity<>(cart, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*
    public ResponseEntity<CartExtendsDTO> findOneById(@PathVariable(value = "id") Integer cartID)
    {
        CartExtendsDTO cart = cartService.findOneById(cartID);
        return cart != null
                ? new ResponseEntity<>(cart, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
         */


}
