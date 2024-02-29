package com.juangp.inditex.infraestructure.web.out;

import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.model.dto.Prices;
import com.juangp.inditex.infraestructure.persistence.entity.PricesEntity;
import com.juangp.inditex.infraestructure.persistence.mapping.PriceEntityMapper;
import com.juangp.inditex.infraestructure.persistence.repository.PricesRepository;
import com.juangp.inditex.infraestructure.persistence.repository.PricesRepositoryH2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PricesRepositoryH2Test {

    @Mock
    private PricesRepository pricesRepository;

    @Mock
    private PriceEntityMapper priceEntityMapper;

    @InjectMocks
    private PricesRepositoryH2 pricesRepositoryH2;

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
        when(priceEntityMapper.mapPricesEntityToPrices(result)).thenReturn(expectedResponse);
        // Act
        Prices response= pricesRepositoryH2.findByBrandIdAndProductIdAndDate(brandId,productId,date);
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
            pricesRepositoryH2.findByBrandIdAndProductIdAndDate(brandId,productId,date);
            assertThrows(PriceNotFoundException.class, () -> pricesRepositoryH2.findByBrandIdAndProductIdAndDate(brandId,productId,date));
        }catch (PriceNotFoundException priceNotFoundException){
            assertThrows(PriceNotFoundException.class, () -> pricesRepositoryH2.findByBrandIdAndProductIdAndDate(brandId,productId,date));
        }
    }
}
