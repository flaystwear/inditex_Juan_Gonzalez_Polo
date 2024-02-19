package com.juangp.inditex.infraestructure.web.in.service;

import com.juangp.inditex.application.service.PriceFinder;
import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.dto.Prices;
import com.juangp.inditex.domain.model.in.PricesRequest;
import com.juangp.inditex.domain.model.mapping.PricesResponseMapper;
import com.juangp.inditex.domain.model.out.PricesResponse;
import com.juangp.inditex.infraestructure.persistence.mapping.PriceEntityMapper;
import com.juangp.inditex.infraestructure.persistence.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceFinderImpl implements PriceFinder {
    @Autowired
    PricesRepository pricesRepository;

    @Autowired
    PricesResponseMapper pricesResponseMapper;
    @Autowired
    PriceEntityMapper priceEntityMapper;

    @Override
    public PricesResponse findPricesInditex(PricesRequest pricesRequest) throws PriceNotFoundException{
        Prices prices= priceEntityMapper.mapPricesEntityToPrices(
                pricesRepository.findByBrandIdAndProductIdAndDate(
                pricesRequest.getBrand(),
                pricesRequest.getProduct(),
                pricesRequest.getDate()));
        if(null!=prices){
            return buildResponse(prices);
        }else{
            throw new PriceNotFoundException("Could not find prices for product "
                    +pricesRequest.getProduct()+", "
                    +pricesRequest.getBrand()+" ,as on "
                    +pricesRequest.getDate().toString());
        }
    }

    @Override
    public PricesResponse findPricesInditex(LocalDateTime dateAsOn, Long idProduct, Long idList) throws PriceNotFoundException {
        Prices prices= priceEntityMapper.mapPricesEntityToPrices(
                pricesRepository.findByBrandIdAndProductIdAndDate(
                        idList,
                        idProduct,
                        dateAsOn));
        if(null!=prices){
            return buildResponse(prices);
        }else{
            throw new PriceNotFoundException("Could not find prices for product "
                    +idProduct+", "
                    +idList+" ,as on "
                    +dateAsOn.toString());
        }
    }

    @Override
    public PricesResponse buildResponse(Prices prices) throws TraductionDtoException {
        return pricesResponseMapper.mapPricesToPricesResponse(prices);
    }
}
