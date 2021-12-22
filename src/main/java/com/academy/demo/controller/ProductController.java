package com.academy.demo.controller;

import com.academy.demo.dto.ProductDTO;
import com.academy.demo.dto.ProductFakeDTO;
import com.academy.demo.dto.ProductWithCategoriesDTO;
import com.academy.demo.dto.ProductWithDataDTO;
import com.academy.demo.search.ProductSearch;
import com.academy.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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

    @PostMapping // /api/product
    public ResponseEntity<Void> insert(@RequestBody ProductDTO product)
    {
        LOGGER.info("Product to be stored: {}", product);

        productService.insert(product);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "with-categories") // /api/product/with-categories
    public ResponseEntity<Void> insertWithCollectionOfCategories(@RequestBody ProductWithCategoriesDTO product)
    {
        LOGGER.info("Product with categories: {}", product);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "collection-with-categories") // /api/product/with-categories
    public ResponseEntity<Void> insertWithCollectionOfCategories(@RequestBody List<ProductWithCategoriesDTO> products)
    {
        LOGGER.info("Products with categories: {}", products);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "collection-with-prefix") // /api/product/with-categories
    public ResponseEntity<Void> insertWithCollectionOfCategoriesWithPrefix(@RequestBody ProductWithDataDTO productMap)
    {
        LOGGER.info("Products with categories: {}", productMap);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "insert-with-headers")
    public ResponseEntity<Void> insertWithHeaders(@RequestBody ProductWithCategoriesDTO product,
                                                  @RequestHeader(value = "Authentication", required = false) String authentication,
                                                  @RequestHeader(value = "token") String token)
    {
        LOGGER.info("Request header: Authentication: {} - Token: {}", authentication, token);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "insert-with-headers-bulk")
    public ResponseEntity<Void> insertWithHeadersBulk(@RequestBody ProductWithCategoriesDTO product,
                                                      @RequestHeader Map<String, String> headers) // Authentication: JWT2
    {
        LOGGER.info("Request headers: {}", headers);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "insert-with-headers-bulk-multi-value-map")
    public ResponseEntity<Void> insertWithHeadersBulkMultiValue(@RequestBody ProductWithCategoriesDTO product,
                                                                @RequestHeader MultiValueMap<String, List<String>> values)
    {
        LOGGER.info("Multi value headers: {}", values);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @PostMapping(value = "insert-with-headers-bulk-dedicated-class-mapping")
    public ResponseEntity<Void> insertWithHeadersBulkDedicatedClass(@RequestBody ProductWithCategoriesDTO product,
                                                                    @RequestHeader HttpHeaders httpHeaders)
    {
        LOGGER.info("Http headers: {}", httpHeaders);
        return new ResponseEntity<>(HttpStatus.CREATED); // 201
    }

    @GetMapping(value = "respond-with-headers")
    public ResponseEntity<Map<String, String>> insertWithResponseHeaders()
    {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("message", "This is response body");

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Test-Header", "Test123");

        return new ResponseEntity<>(responseBody, responseHeaders, HttpStatus.OK);
    }

    @GetMapping(value = "fake")
    public ResponseEntity<List<ProductFakeDTO>> getAllFakeProducts()
    {
        List<ProductFakeDTO> products = productService.findAllFake();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
