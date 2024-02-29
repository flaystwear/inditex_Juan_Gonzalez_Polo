package com.juangp.inditex.application.useCases;

import com.juangp.inditex.application.ports.in.PriceFinder;
import com.juangp.inditex.application.ports.out.FindPricesPort;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.dto.Prices;
import com.juangp.inditex.domain.model.in.PricesRequest;
import com.juangp.inditex.domain.model.out.PricesResponse;
import com.juangp.inditex.domain.services.mapper.PricesResponseMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceFinderImpl implements PriceFinder {
    private static final Logger logger = LoggerFactory.getLogger(PriceFinderImpl.class);

    private final FindPricesPort h2;

    private final PricesResponseMapper pricesResponseMapper;

    @Override
    public PricesResponse findPricesInditex(PricesRequest pricesRequest) {
        Prices prices = h2.findByBrandIdAndProductIdAndDate(
                pricesRequest.getBrand(),
                pricesRequest.getProduct(),
                pricesRequest.getDate()
        );
        try {
            if (null == prices) {
                throw new TraductionDtoException("Translation came null");
            }
            return buildResponse(prices);
        } catch (Exception e) {
            logger.error("Error translating to DTO from working object to response. Error: {}", e.getMessage());
            throw new TraductionDtoException(String.format("Error traduciendo datos: %s",
                    prices));
        }
    }

    @Override
    public PricesResponse findPricesInditex(LocalDateTime dateAsOn, Long idProduct, Long idList) {
        //Implementación en caso de que los parámetros no vengan en un RequestBody
        Prices prices = h2.findByBrandIdAndProductIdAndDate(
                idList,
                idProduct,
                dateAsOn
        );
        try {
            if (null == prices) {
                throw new TraductionDtoException("Translation came null");
            }
            return buildResponse(prices);
        } catch (Exception e) {
            logger.error("Error translating to DTO from working object to response. Error: {}", e.getMessage());
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
