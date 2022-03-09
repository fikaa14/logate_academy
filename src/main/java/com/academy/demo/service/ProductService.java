package com.academy.demo.service;

import com.academy.demo.dto.ProductDTO;
import com.academy.demo.entity.ProductEntity;
import com.academy.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductFakerService fakerService;

    @Autowired
    private ProductRepository productRepository;

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

    public List<ProductEntity> search(String term)
    {
        return productRepository.findByNameStartingWithOrDescriptionStartingWith(term, term);
    }

    public List<ProductEntity> searchPageable(String term, Pageable pageable)
    {
        Page<ProductEntity> productsPageable =
                productRepository.findByNameStartingWithOrDescriptionStartingWith(term, term, pageable);

        List<ProductEntity> products = productsPageable.getContent();
        return products;
    }
}
