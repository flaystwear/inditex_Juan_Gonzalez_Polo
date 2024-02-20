package com.juangp.inditex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = InditexPricesAplication.class)
public class InditexPricesApplicationIntegrationTest {

    @Autowired
    private InditexPricesAplication inditexPricesApplication;

    @Test
    public void contextLoads() {
        assertNotNull(inditexPricesApplication);
    }
}