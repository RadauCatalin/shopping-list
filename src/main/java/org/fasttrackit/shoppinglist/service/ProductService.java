package org.fasttrackit.shoppinglist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.persistance.ProductRepository;
import org.fasttrackit.shoppinglist.transfer.productRequests.GetProductsRequest;
import org.fasttrackit.shoppinglist.transfer.productRequests.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper) {
        this.productRepository = productRepository;
    }

    public Product createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}", request);
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setBought(request.isBought());

        return productRepository.save(product);
    }

    public Product getProduct(long id) {

        LOGGER.info("Retrevieng product {}", id);
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " does not exist."));
    }

    public Page<Product> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products: {}", request);
        if (Objects.nonNull(request) && Objects.nonNull(request.getPartialName())) {
            return productRepository.findByPartialName(request.getPartialName(), pageable);
        } else {
            return productRepository.findAll(pageable);
        }
    }

    public Product updateProduct(long id, SaveProductRequest request) {
        LOGGER.info("Updating product {}: {}", id, request);
        Product product = getProduct(id);
        BeanUtils.copyProperties(request, product);

        return productRepository.save(product);
    }

    public void deleteProduct(long id) {
        LOGGER.info("Deleting product {}", id);
        productRepository.deleteById(id);
        LOGGER.info("Deleted product {}", id);
    }
}