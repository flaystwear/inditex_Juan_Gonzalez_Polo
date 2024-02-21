package com.juangp.inditex.domain.model.service;

import com.juangp.inditex.application.useCase.ValidateRequestData;
import com.juangp.inditex.domain.exception.RequestNotAcceptableException;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.infraestructure.web.service.PriceFinderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidateRequestDataImpl implements ValidateRequestData {
    private static final Logger logger = LoggerFactory.getLogger(PriceFinderImpl.class);
    @Override
    public void checkPricesRequest(PricesRequest pricesRequest){
        if(null==pricesRequest
                || null==pricesRequest.getBrand()
                || null==pricesRequest.getDate()
                || null==pricesRequest.getProduct()){
            logger.error("Request body missing some element");
            throw new RequestNotAcceptableException("Missing key elements on the Request Body");
        }
    }
    @Override
    public void checkPricesRequest(LocalDateTime date, Long productId, Long brandId){
        if(null==date
                || null==productId
                || null==brandId){
            logger.error("Request body missing some element");
            throw new RequestNotAcceptableException("Missing key elements on the Request Body");
        }
    }
}
