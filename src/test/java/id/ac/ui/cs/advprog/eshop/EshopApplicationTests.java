package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTests {

    @Test
    void contextLoads() {
        EshopApplication.main(new String[] {});
        assertThat(EshopApplication.class).isNotNull();
    }

}
