package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplementationTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }


    @Test
    void TestFindAll() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productList.add(product1);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> allProducts = productService.findAll();

        assertEquals(1, allProducts.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testCreateAndDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        productService.create(product);
        productService.delete(product.getProductId());

        verify(productRepository, times(1)).create(product);
        verify(productRepository, times(1)).delete(product.getProductId());
    }

    @Test
    void testUpdate() {
        Product existingProduct = new Product();
        existingProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        existingProduct.setProductName("Sampo Cap Bambang");
        existingProduct.setProductQuantity(100);

        String updatedProductName = "Kecap Cap Capung";
        int updatedProductQuantity = 120;

        Product updatedProduct = new Product();
        updatedProduct.setProductId(existingProduct.getProductId());
        updatedProduct.setProductName("Kecap Cap Capung");
        updatedProduct.setProductQuantity(120);

        productService.update(updatedProduct.getProductId(), updatedProduct);

        verify(productRepository, times(1)).update(updatedProduct.getProductId(), updatedProduct);
    }

    @Test
    void testFindProductById_ExistingProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product expectedProduct = new Product();
        expectedProduct.setProductId(productId);
        expectedProduct.setProductName("Sampo Cap Bambang");
        expectedProduct.setProductQuantity(100);

        when(productRepository.findById(productId)).thenReturn(expectedProduct);

        Product retrievedProduct = productService.findById(productId);

        assertEquals(expectedProduct, retrievedProduct);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindProductById_NonExistingProduct() {
        String productId = "not-existing-id";

        when(productRepository.findById(productId)).thenReturn(null);

        Product retrievedProduct = productService.findById(productId);

        assertNull(retrievedProduct);
        verify(productRepository, times(1)).findById(productId);
    }
}