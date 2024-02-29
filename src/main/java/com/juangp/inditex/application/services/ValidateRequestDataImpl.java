package com.juangp.inditex.application.services;

import com.juangp.inditex.domain.exception.RequestNotAcceptableException;
import com.juangp.inditex.domain.model.in.PricesRequest;
import com.juangp.inditex.domain.services.ValidateRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ValidateRequestDataImpl implements ValidateRequestData {
    private static final Logger logger = LoggerFactory.getLogger(ValidateRequestDataImpl.class);

    @Override
    public void checkPricesRequest(PricesRequest pricesRequest) {
        if (null == pricesRequest
                || null == pricesRequest.getBrand()
                || null == pricesRequest.getDate()
                || null == pricesRequest.getProduct()) {
            logger.error("Request body missing some element");
            throw new RequestNotAcceptableException("Missing key elements on the Request Body");
        }
    }

    @Override
    public void checkPricesRequest(LocalDateTime date, Long productId, Long brandId) {
        if (null == date
                || null == productId
                || null == brandId) {
            logger.error("Request body missing some element");
            throw new RequestNotAcceptableException("Missing key elements on the Request Body");
        }
    }
}
