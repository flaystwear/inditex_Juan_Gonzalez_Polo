package com.juangp.inditex.application.useCase.prices;

import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.prices.dto.Prices;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.domain.model.prices.out.PricesResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceFinder  {
    PricesResponse findPricesInditex(PricesRequest pricesRequest) ;
    PricesResponse findPricesInditex(LocalDateTime dateAsOn, Long idProduct, Long idList);
    PricesResponse buildResponse(Prices prices);
}
