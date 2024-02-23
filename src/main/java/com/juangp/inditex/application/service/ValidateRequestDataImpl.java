package com.juangp.inditex.application.service;

import com.juangp.inditex.application.useCase.in.ValidateRequestData;
import com.juangp.inditex.domain.exception.RequestNotAcceptableException;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Qualifier("validationPrices")
public class ValidateRequestDataImpl implements ValidateRequestData {
    private static final Logger logger = LoggerFactory.getLogger(ValidateRequestDataImpl.class);
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
