package com.juangp.inditex.infraestructure.web.out;

import com.juangp.inditex.application.useCase.in.ValidateRequestData;
import com.juangp.inditex.application.useCase.out.H2Database;
import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.prices.dto.FullPrice;
import com.juangp.inditex.domain.model.prices.dto.Prices;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.domain.model.prices.out.PricesResponse;
import com.juangp.inditex.infraestructure.persistence.entity.PricesEntity;
import com.juangp.inditex.infraestructure.persistence.mapping.PriceEntityMapper;
import com.juangp.inditex.infraestructure.persistence.repository.PricesRepository;
import com.juangp.inditex.infraestructure.web.in.contrroller.PricesController;
import com.juangp.inditex.infraestructure.web.in.service.PriceFinderImpl;
import com.juangp.inditex.infraestructure.web.out.service.H2Impl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class H2ImplTest {

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PriceEntityMapper priceEntityMapper;
    @InjectMocks
    private H2Impl h2Impl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void testfindByBrandIdAndProductIdAndDate_WithValidRequest_ShouldReturnOKResponse() {
        // Arrange
        Long brandId = 1L;
        Long productId = 1L;
        LocalDateTime date = LocalDateTime.now();
        Prices expectedResponse = new Prices(1L,1L,1L,date, date,1L,  BigDecimal.valueOf(1),"EUR");
        PricesEntity result=new PricesEntity(1L,1L,date,date,1L, 1L,1L,  BigDecimal.valueOf(1),"EUR");
        when(pricesRepository.findByBrandIdAndProductIdAndDate(any(), any(), any())).thenReturn(result);
        // Act
        Prices response= h2Impl.findByBrandIdAndProductIdAndDate(brandId,productId,date);
        // Assert
        assertEquals(expectedResponse,response);
    }
    @Test
    void testfindByBrandIdAndProductIdAndDate_WithValidRequest_ShouldReturnNotFound() {
        // Arrange
        Long brandId = 1L;
        Long productId = 1L;
        LocalDateTime date = LocalDateTime.now();
        when(pricesRepository.findByBrandIdAndProductIdAndDate(any(), any(), any())).thenReturn(null);
        // Act & Assert
        try{
            h2Impl.findByBrandIdAndProductIdAndDate(brandId,productId,date);
            assertThrows(PriceNotFoundException.class, () -> h2Impl.findByBrandIdAndProductIdAndDate(brandId,productId,date));
        }catch (PriceNotFoundException priceNotFoundException){
            assertThrows(PriceNotFoundException.class, () -> h2Impl.findByBrandIdAndProductIdAndDate(brandId,productId,date));
        }
    }
}
