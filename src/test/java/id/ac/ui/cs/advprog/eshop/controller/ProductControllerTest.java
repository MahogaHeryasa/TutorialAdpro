package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {}

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        when(productService.create(product)).thenReturn(product);
        String redirectURL = productController.createProductPost(product, model);
        assertEquals("redirect:list", redirectURL);
    }

    @Test
    void  testProductListPage() {
        when(productService.findAll()).thenReturn(null);
        String viewName = productController.productListPage(model);
        assertEquals("ProductList", viewName);
    }

    @Test
    void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        String redirectURL = productController.deleteProduct(productId);
        assertEquals("redirect:/product/list", redirectURL);
    }

    @Test
    void testEditProductPage() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.findById(productId)).thenReturn(product);

        String viewName = productController.editProductPage(productId, model);
        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.findById(productId)).thenReturn(product);

        String redirectURL = productController.editProduct(productId, "Sampo Cap Bambi", 120);
        assertEquals("redirect:/product/list", redirectURL);
    }
}
