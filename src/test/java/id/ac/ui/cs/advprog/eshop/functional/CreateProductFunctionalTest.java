package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;
import java.util.List;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {

    @LocalServerPort
    private int serverPort;

    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest() {
        baseUrl = String.format("%s:%d", testBaseUrl, serverPort);
    }

    @Test
    void pageTitle_isCorrect(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");
        String pageTitle = driver.getTitle();

        assertEquals("Create New Product", pageTitle);
    }

    @Test
    void formFields_arePresent(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement productNameField = driver.findElement(By.id("nameInput"));
        WebElement productQuantityField = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        assertTrue(productNameField.isDisplayed());
        assertTrue(productQuantityField.isDisplayed());
        assertTrue(submitButton.isDisplayed());
    }

    @Test
    void createProduct_successful(ChromeDriver driver) throws Exception {
        driver.get(baseUrl + "/product/create");

        WebElement productNameField = driver.findElement(By.id("nameInput"));
        WebElement productQuantityField = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.tagName("button"));

        productNameField.sendKeys("Test Product");
        productQuantityField.sendKeys("10");
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/product/list"));

        WebElement productListTable = driver.findElement(By.tagName("table"));
        List<WebElement> productListRows = productListTable.findElements(By.tagName("tr"));

        boolean productFound = false;
        for (WebElement row : productListRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 2) { // Ensure that the row contains at least two cells (name and quantity)
                String productName = cells.get(0).getText();
                String productQuantity = cells.get(1).getText();
                if (productName.equals("Test Product") && productQuantity.equals("10")) {
                    productFound = true;
                    break;
                }
            }
        }

        assertTrue(productFound, "Newly created product should be visible in the product list");
    }
}