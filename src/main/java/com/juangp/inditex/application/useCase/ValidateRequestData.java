package com.juangp.inditex.application.useCase;

import com.juangp.inditex.domain.exception.RequestNotAcceptableException;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.infraestructure.web.in.service.PriceFinderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ValidateRequestData {
    private static final Logger logger = LoggerFactory.getLogger(PriceFinderImpl.class);
    public void checkPricesRequest(PricesRequest pricesRequest){
        if(null==pricesRequest
                || null==pricesRequest.getBrand()
                || null==pricesRequest.getDate()
                || null==pricesRequest.getProduct()){
            logger.error("Request body missing some element");
            throw new RequestNotAcceptableException("Missing key elements on the Request Body");
        }
    }
}
