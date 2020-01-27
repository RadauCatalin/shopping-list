package org.fasttrackit.shoppinglist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.domain.ShoppingList;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.persistance.ProductRepository;
import org.fasttrackit.shoppinglist.persistance.ShoppingListRepository;
import org.fasttrackit.shoppinglist.transfer.RemoveFromListRequest;
import org.fasttrackit.shoppinglist.transfer.productRequests.GetProductsRequest;
import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;
import org.fasttrackit.shoppinglist.transfer.productRequests.SaveProductRequest;
import org.fasttrackit.shoppinglist.transfer.shoppingListRequests.AddToShoppingListRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ShoppingListService shoppingListService;
    private final ShoppingListRepository shoppingListRepository;
    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper;

    @Autowired

    public ProductService(ShoppingListService shoppingListService, ShoppingListRepository shoppingListRepository, ProductRepository productRepository, ObjectMapper objectMapper) {
        this.shoppingListService = shoppingListService;
        this.shoppingListRepository = shoppingListRepository;
        this.productRepository = productRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}", request);
        Product product = objectMapper.convertValue(request, Product.class);
        AddToShoppingListRequest addRequest = new AddToShoppingListRequest();
        addRequest.setListId(request.getListId());
        addRequest.setProductId(product.getId());
        productRepository.save(product);
        shoppingListService.addProductToShoppingList(addRequest, product);

    }

    public Product getProduct(long id) {

        LOGGER.info("Retrieving product {}", id);
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product " + id + " does not exist."));
    }

    public Page<ProductResponse> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products: {}", request);
        Page<Product> products;
        if (Objects.nonNull(request) && Objects.nonNull(request.getPartialName())) {
            products = productRepository.findByPartialName(request.getPartialName(), pageable);
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

    public void deleteProduct(RemoveFromListRequest request) {
        LOGGER.info("Deleting product {}", request);
        ShoppingList shoppingList = shoppingListRepository.findById(request.getListID()).orElseThrow(() -> new ResourceNotFoundException("List " + request.getListID() + " does not exist."));
        Product product = productRepository.findById(request.getProductID()).orElseThrow(() -> new ResourceNotFoundException("Product " + request.getProductID() + " does not exist."));
        shoppingList.removeFromShoppingList(product);
        shoppingListRepository.save(shoppingList);
        LOGGER.info("Deleted product {}", request);
    }
}