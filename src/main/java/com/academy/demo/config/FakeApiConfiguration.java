package com.academy.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fake-store")
public class FakeApiConfiguration {

    private String url;
    private String productPart;
    private String categoryPart;
    private String cartPart;

    public FakeApiConfiguration fakeApiConfiguration(){
        return new FakeApiConfiguration();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProductPart() {
        return productPart;
    }

    public void setProductPart(String productPart) {
        this.productPart = productPart;
    }

    public String getCategoryPart() {
        return categoryPart;
    }

    public void setCategoryPart(String categoryPart) {
        this.categoryPart = categoryPart;
    }

    public String getCartPart() {
        return cartPart;
    }

    public void setCartPart(String cartPart) {
        this.cartPart = cartPart;
    }
}
