package id.ac.ui.cs.advprog.eshop.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @BeforeEach
    void setUp() {}

    @InjectMocks
    private HomeController homeController;

    @Test
    void testHomePage() {
        String viewName = homeController.homePage();
        assertEquals("homePage", viewName);
    }
}