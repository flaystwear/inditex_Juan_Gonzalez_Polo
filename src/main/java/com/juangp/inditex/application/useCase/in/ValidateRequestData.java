package com.juangp.inditex.application.useCase.in;

import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ValidateRequestData {
    void checkPricesRequest(PricesRequest pricesRequest);
    void checkPricesRequest(LocalDateTime date, Long productId, Long brandId);
}
