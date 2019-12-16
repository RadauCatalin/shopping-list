package org.fasttrackit.shoppinglist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.persistance.ProductRepository;
import org.fasttrackit.shoppinglist.transfer.productRequests.GetProductsRequest;
import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;
import org.fasttrackit.shoppinglist.transfer.productRequests.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ObjectMapper objectMapper, ObjectMapper objectMapper1) {
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    public Product createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}", request);
        Product product = objectMapper.convertValue(request, Product.class);
        return productRepository.save(product);
    }

    public Product getProduct(long id) {

        LOGGER.info("Retrevieng product {}", id);
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " does not exist."));
    }

    public Page<ProductResponse> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products: {}", request);
        Page<Product> products;
        if (Objects.nonNull(request) && Objects.nonNull(request.getPartialName())) {
             products =productRepository.findByPartialName(request.getPartialName(), pageable);
        } else {
            products = productRepository.findAll(pageable);
        }
        List<ProductResponse> productResponses = new ArrayList<>();

        for (Product product : products.getContent()) {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(product.getId());
            productResponse.setName(product.getName());
            productResponse.setPrice(product.getPrice());
            productResponse.setBought(product.isBought());

            productResponses.add(productResponse);
        }
        return new PageImpl<>(productResponses, pageable, products.getTotalElements());
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