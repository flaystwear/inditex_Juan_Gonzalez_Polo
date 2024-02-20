package com.juangp.inditex.application.service.prices;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.juangp.inditex.domain.exception.RequestNotAcceptableException;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    /**
     * Method under test:
     * {@link ValidateRequestDataImpl#checkPricesRequest(LocalDateTime, Long, Long)}
     */
    @Test
    void testCheckPricesRequest2() {
        // Arrange, Act and Assert
        assertThrows(RequestNotAcceptableException.class,
                () -> (new ValidateRequestDataImpl()).checkPricesRequest(null, null, null));
    }

    /**
     * Method under test:
     * {@link ValidateRequestDataImpl#checkPricesRequest(LocalDateTime, Long, Long)}
     */
    @Test
    void testCheckPricesRequest3() {
        // Arrange
        ValidateRequestDataImpl validateRequestDataImpl = new ValidateRequestDataImpl();

        // Act and Assert
        assertThrows(RequestNotAcceptableException.class,
                () -> validateRequestDataImpl.checkPricesRequest(LocalDate.of(1970, 1, 1).atStartOfDay(), null, null));
    }

    /**
     * Method under test:
     * {@link ValidateRequestDataImpl#checkPricesRequest(LocalDateTime, Long, Long)}
     */
    @Test
    void testCheckPricesRequest4() {
        // Arrange
        ValidateRequestDataImpl validateRequestDataImpl = new ValidateRequestDataImpl();

        // Act and Assert
        assertThrows(RequestNotAcceptableException.class,
                () -> validateRequestDataImpl.checkPricesRequest(LocalDate.of(1970, 1, 1).atStartOfDay(), 1L, null));
    }
}
