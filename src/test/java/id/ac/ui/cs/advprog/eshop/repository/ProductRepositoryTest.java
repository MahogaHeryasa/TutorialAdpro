package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
         Product product = new Product();
         product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
         product.setProductName("Sampo Cap Bambang");
         product.setProductQuantity(100);
         productRepository.create(product);

         Iterator<Product> productIterator = productRepository.findAll();
         assertTrue(productIterator.hasNext());
         Product savedProduct = productIterator.next();
         assertEquals(product.getProductId(), savedProduct.getProductId());
         assertEquals(product.getProductName(), savedProduct.getProductName());
         assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void TestFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testCreateAndDelete() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

        productRepository.delete(product.getProductId());
        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());

    }

    void testCreateAndDeleteNotExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
        
        Product NotExistingProduct = new Product();
        NotExistingProduct.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        NotExistingProduct.setProductName("Sampo Cap Usep");
        NotExistingProduct.setProductQuantity(50);
        productRepository.delete(NotExistingProduct.getProductId());
        productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

    @Test
    void testCreateAndEdit() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());

        String productName = savedProduct.getProductName();
        int productQuantity = savedProduct.getProductQuantity();

        Product updatedProduct = new Product();
        updatedProduct.setProductId(product.getProductId());
        updatedProduct.setProductName("Kecap Cap Bagas");
        updatedProduct.setProductQuantity(120);
        Product editedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        productRepository.update(updatedProduct.getProductId(), updatedProduct);

        assertNotEquals(editedProduct.getProductName(),productName);
        assertNotEquals(editedProduct.getProductQuantity(),productQuantity);
        assertEquals(editedProduct.getProductId(),updatedProduct.getProductId());
        assertEquals(editedProduct.getProductName(),updatedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(),updatedProduct.getProductQuantity());
    }

    @Test
    void testFindProductById_ExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product retrievedProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(retrievedProduct);
        assertEquals(product.getProductId(), retrievedProduct.getProductId());
        assertEquals(product.getProductName(), retrievedProduct.getProductName());
        assertEquals(product.getProductQuantity(), retrievedProduct.getProductQuantity());
    }

    @Test
    void testFindProductById_NonExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product retrievedProduct = productRepository.findById("123-123-123");

        assertNotEquals(product, retrievedProduct);
        assertNull(retrievedProduct);
    }
}