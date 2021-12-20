package com.academy.demo.controller;

import com.academy.demo.dto.ProductDTO;
import com.academy.demo.search.ProductSearch;
import com.academy.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    /**
     * Getting all products | GET : /api/product
     *
     * @return ResponseEntity with List of ProductDTO objects and Http Status Code 200
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> all()
    {
        List<ProductDTO> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Getting product by unique identifier | GET : /api/product/{id}
     *
     * @param productId product identifier
     * @return ResponseEntity with ProductDTO object and Http Status Code (200 | 400)
     */
    @GetMapping(value = "{id}")
    public ResponseEntity<ProductDTO> one(@PathVariable(value = "id") Integer productId)
    {
        ProductDTO product = productService.findOneById(productId);
        return product != null
            ? new ResponseEntity<>(product, HttpStatus.OK)
            : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Getting products by category | GET : /api/product/by-category?category=####
     *
     * @param category category name as query parameter
     * @return ResponseEntity with List of ProductDTO objects and Http Status Code 200
     */
    @GetMapping(value = "by-category")
    public ResponseEntity<List<ProductDTO>> byCategory(@RequestParam(value = "category") String category)
    {
        List<ProductDTO> products = productService.findByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Getting products by multiple categories
     *
     * @param categories category as query parameters with multiple values
     * @return ResponseEntity with List of ProductDTO objects and Http Status Code 200
     */
    @GetMapping(value = "by-categories")
    public ResponseEntity<List<ProductDTO>> byCategories(@RequestParam(value = "category") List<String> categories)
    {
        LOGGER.info("Categories: {}", categories);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Getting products by unknown parameters | GET : /api/product/search/unknown
     *
     * @param parameters undefined query parameters
     * @return ResponseEntity with List of ProductDTO objects and Http Status Code 200
     */
    @GetMapping(value = "search/unknown")
    public ResponseEntity<List<ProductDTO>> searchByUnknown(@RequestParam Map<String, Object> parameters)
    {
        LOGGER.info("Parameters: {}", parameters);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Getting products by known parameters | GET : /api/product/search
     *
     * @param productSearch defined query parameters
     * @return ResponseEntity with List of ProductDTO objects and Http Status Code 200
     */
    @GetMapping(value = "search")
    public ResponseEntity<List<ProductDTO>> search(ProductSearch productSearch)
    {
        LOGGER.info("Product search: {}", productSearch);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
