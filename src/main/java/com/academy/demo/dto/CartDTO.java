package com.academy.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    private Integer id;
    private Integer userId;
    private String date;
    private List<ProductWithQuantity> products = new ArrayList<>();

    @JsonProperty("__v")
    private Integer v;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ProductWithQuantity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithQuantity> products) {
        this.products = products;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
