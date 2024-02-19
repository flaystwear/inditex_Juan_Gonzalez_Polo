package com.juangp.inditex.application.service;

import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.dto.Prices;
import com.juangp.inditex.domain.model.in.PricesRequest;
import com.juangp.inditex.domain.model.out.PricesResponse;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PriceFinder  {
    PricesResponse findPricesInditex(PricesRequest pricesRequest) throws PriceNotFoundException;
    PricesResponse findPricesInditex(LocalDateTime dateAsOn, Long idProduct, Long idList) throws PriceNotFoundException;
    PricesResponse buildResponse(Prices prices) throws TraductionDtoException;
}
