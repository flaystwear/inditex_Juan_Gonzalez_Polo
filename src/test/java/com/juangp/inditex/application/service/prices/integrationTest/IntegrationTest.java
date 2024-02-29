package com.juangp.inditex.application.service.prices.integrationTest;

import com.juangp.inditex.domain.model.in.PricesRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testEndpoint_ShouldReturnNotFound() {
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        PricesRequest request=new PricesRequest(1L,35455L,date);
        String result = this.restTemplate.postForObject("http://localhost:" + port + "/api/v2/prices",request, String.class);
        assertThat(result).isEqualTo("Could not find prices for product: 35455, brand: 1, date: 2020-06-14T10:00");
    }
}
