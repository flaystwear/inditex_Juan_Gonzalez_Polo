package com.juangp.inditex.infraestructure.web.in.service;

import com.juangp.inditex.application.useCase.in.PriceFinder;
import com.juangp.inditex.application.useCase.out.H2Database;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.prices.dto.Prices;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.domain.model.service.mapping.PricesResponseMapper;
import com.juangp.inditex.domain.model.prices.out.PricesResponse;
import com.juangp.inditex.infraestructure.web.out.service.H2Impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Qualifier("pricesFinder")
public class PriceFinderImpl implements PriceFinder {
    private static final Logger logger = LoggerFactory.getLogger(PriceFinderImpl.class);
    @Autowired
    @Qualifier("h2Prices")
    H2Database h2;

    @Autowired
    PricesResponseMapper pricesResponseMapper;

    @Override
    public PricesResponse findPricesInditex(PricesRequest pricesRequest){
        Prices prices=h2.findByBrandIdAndProductIdAndDate(
                pricesRequest.getBrand(),
                pricesRequest.getProduct(),
                pricesRequest.getDate()
        );
        try{
            if(null==prices){
                throw new TraductionDtoException("Translation came null");
            }
            return buildResponse(prices);
        } catch (Exception e){
            logger.error("Error translating to DTO from working object to response. Error: {}",e.getMessage());
            throw new TraductionDtoException(String.format("Error traduciendo datos: %s",
                    prices));
        }
    }

    @Override
    public PricesResponse findPricesInditex(LocalDateTime dateAsOn, Long idProduct, Long idList) {
        //Implementación en caso de que los parámetros no vengan en un RequestBody
        Prices prices=h2.findByBrandIdAndProductIdAndDate(
                idList,
                idProduct,
                dateAsOn
        );
        try{
            if(null==prices){
                throw new TraductionDtoException("Translation came null");
            }
            return buildResponse(prices);
        } catch (Exception e){
            logger.error("Error translating to DTO from working object to response. Error: {}",e.getMessage());
            throw new TraductionDtoException(String.format("Error traduciendo datos: %s",
                    prices));
        }
    }

    @Override
    public PricesResponse buildResponse(Prices prices) {
        //Se traduce formato DTO de base de datos a ResponseBody
        return pricesResponseMapper.mapPricesToPricesResponse(prices);
    }
}
