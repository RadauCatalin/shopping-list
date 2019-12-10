package org.fasttrackit.shoppinglist;

import org.fasttrackit.shoppinglist.domain.Product;
import org.fasttrackit.shoppinglist.exception.ResourceNotFoundException;
import org.fasttrackit.shoppinglist.service.ProductService;
import org.fasttrackit.shoppinglist.transfer.productRequests.SaveProductRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductService productService;

    @Test
    public void testCreateProduct_whenValidRequest_thenProductIsSaved() {
        createProduct();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateProduct_whenInvalidRequest_thenThrowException() {
        SaveProductRequest request = new SaveProductRequest();

        productService.createProduct(request);
    }

    @Test
    public void testGetProduct_whenExistingProduct_thenReturnProduct() {
        Product createdProduct = createProduct();

        Product product = productService.getProduct(createdProduct.getId());

        assertThat(product, notNullValue());
        assertThat(product.getId(), is(createdProduct.getId()));
        assertThat(product.getName(), is(createdProduct.getName()));
        assertThat(product.getPrice(), is(createdProduct.getPrice()));

    }

    @Test(expected = ResourceNotFoundException.class)
    public void testGetProduct_whenNonExistingProduct_thenTrowResourcesNotFoundException() {
        productService.getProduct(999999999999999L);
    }

    @Test
    public void testUpdateProduct_whenValidRequest_thenReturnUpdatedProduct() {
        Product createdProduct = createProduct();

        SaveProductRequest request = new SaveProductRequest();
        request.setName(createdProduct.getName() + "Updated");
        request.setPrice(createdProduct.getPrice() + 10.15);

        Product updatedProduct = productService.updateProduct(createdProduct.getId(), request);

        assertThat(updatedProduct, notNullValue());
        assertThat(updatedProduct.getId(), is(createdProduct.getId()));
        assertThat(updatedProduct.getName(), is(request.getName()));
        assertThat(updatedProduct.getPrice(), is(request.getPrice()));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testDeleteProduct_whenExistingProduct_thenProductIsDeleted() {
        Product product = createProduct();

        productService.deleteProduct(product.getId());
        productService.getProduct(product.getId());
    }


    private Product createProduct() {
        SaveProductRequest request = new SaveProductRequest();
        request.setName("Banana " + System.currentTimeMillis());
        request.setPrice(5.00);
        request.setBought(false);

        Product createdProduct = productService.createProduct(request);

        assertThat(createdProduct, notNullValue());
        assertThat(createdProduct.getId(), notNullValue());
        assertThat(createdProduct.getId(), greaterThan(0L));
        assertThat(createdProduct.getName(), is(request.getName()));
        assertThat(createdProduct.getPrice(), is(request.getPrice()));

        return createdProduct;
    }
}