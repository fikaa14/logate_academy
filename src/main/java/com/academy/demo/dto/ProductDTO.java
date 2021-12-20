package com.academy.demo.dto;

public class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private String category;

    /*
    {
        "id": 2,
        "name": "string",
        "price": 100.12
        "category": "string"
    }
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
