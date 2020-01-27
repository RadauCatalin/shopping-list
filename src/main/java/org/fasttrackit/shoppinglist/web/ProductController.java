package org.fasttrackit.shoppinglist.web;

import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.service.ProductService;
import org.fasttrackit.shoppinglist.transfer.RemoveFromListRequest;
import org.fasttrackit.shoppinglist.transfer.productRequests.GetProductsRequest;
import org.fasttrackit.shoppinglist.transfer.productRequests.ProductResponse;
import org.fasttrackit.shoppinglist.transfer.productRequests.SaveProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Valid SaveProductRequest request) {
        productService.createProduct(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getProducts(GetProductsRequest request, Pageable pageable) {
        Page<ProductResponse> products = productService.getProducts(request, pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid SaveProductRequest request) {
        Product product = productService.updateProduct(id, request);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Product> deleteProduct(@RequestBody @Valid RemoveFromListRequest request) {
        productService.deleteProduct(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}