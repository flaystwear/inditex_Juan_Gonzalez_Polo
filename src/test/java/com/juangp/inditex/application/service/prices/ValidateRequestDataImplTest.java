package com.juangp.inditex.application.service.prices;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.juangp.inditex.domain.exception.RequestNotAcceptableException;
import com.juangp.inditex.domain.model.in.PricesRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.juangp.inditex.application.services.ValidateRequestDataImpl;
import org.junit.jupiter.api.Test;

class ValidateRequestDataImplTest {
    /**
     * Method under test:
     * {@link ValidateRequestDataImpl#checkPricesRequest(PricesRequest)}
     */
    private final ValidateRequestDataImpl validator = new ValidateRequestDataImpl();

    @Test
    void testCheckPricesRequest_WithMissingBrand_ShouldThrowRequestNotAcceptableException() {
        // Arrange
        PricesRequest request = new PricesRequest();
        request.setDate(LocalDateTime.now());
        request.setProduct(1L);

        // Act & Assert
        assertThrows(RequestNotAcceptableException.class, () -> validator.checkPricesRequest(request));
    }

    @Test
    void testCheckPricesRequest_WithMissingDate_ShouldThrowRequestNotAcceptableException() {
        // Arrange
        PricesRequest request = new PricesRequest();
        request.setBrand(1L);
        request.setProduct(1L);

        // Act & Assert
        assertThrows(RequestNotAcceptableException.class, () -> validator.checkPricesRequest(request));
    }

    @Test
    void testCheckPricesRequest_WithMissingProduct_ShouldThrowRequestNotAcceptableException() {
        // Arrange
        PricesRequest request = new PricesRequest();
        request.setBrand(1L);
        request.setDate(LocalDateTime.now());

        // Act & Assert
        assertThrows(RequestNotAcceptableException.class, () -> validator.checkPricesRequest(request));
    }

    @Test
    void testCheckPricesRequest_WithValidRequest_ShouldNotThrowException() {
        // Arrange
        PricesRequest request = new PricesRequest();
        request.setBrand(1L);
        request.setDate(LocalDateTime.now());
        request.setProduct(1L);

        // Act & Assert
        validator.checkPricesRequest(request); // No debería lanzar ninguna excepción
    }
}
