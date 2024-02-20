package com.juangp.inditex.infraestructure.web.in.service;

import com.juangp.inditex.application.useCase.prices.PriceFinder;
import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.prices.dto.Prices;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.domain.model.prices.mapping.PricesResponseMapper;
import com.juangp.inditex.domain.model.prices.out.PricesResponse;
import com.juangp.inditex.infraestructure.persistence.entity.PricesEntity;
import com.juangp.inditex.infraestructure.persistence.mapping.PriceEntityMapper;
import com.juangp.inditex.infraestructure.persistence.repository.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PriceFinderImpl implements PriceFinder {
    private static final Logger logger = LoggerFactory.getLogger(PriceFinderImpl.class);
    @Autowired
    PricesRepository pricesRepository;

    @Autowired
    PricesResponseMapper pricesResponseMapper;
    @Autowired
    PriceEntityMapper priceEntityMapper;

    @Override
    public PricesResponse findPricesInditex(PricesRequest pricesRequest){
        PricesEntity pricesEntity=new PricesEntity();
        try{
            /*Busqueda en base de datos de la informacion de prices solicitada
            * y conversión al object DTO Prices para tratamiento interno
            * */
             pricesEntity= pricesRepository.findByBrandIdAndProductIdAndDate(
                    pricesRequest.getBrand(),
                    pricesRequest.getProduct(),
                    pricesRequest.getDate());
        }catch (Exception e){
            logger.error("Error translating to DTO from database");
            throw new TraductionDtoException(String.format("Error traduciendo los datos de la base de datos: %s",
                    e.getMessage()));
        }
        if (null != pricesEntity) {
            /*Se comprueba que haya encontrado algún precio, si no, se devuelve la excepción con
             * código 404 not found.
             * Si lo encuentra, se convierte en el formato de respuesta correspondiente
             */
            try{
                Prices prices = priceEntityMapper.mapPricesEntityToPrices(pricesEntity);
                return buildResponse(prices);
            } catch (Exception e){
                logger.error("Error translating to DTO from working object to response");
                throw new TraductionDtoException(String.format("Error traduciendo datos: %s",
                        pricesEntity));
            }
        } else {
            logger.error("No prices found in database");
            throw new PriceNotFoundException(String.format("Could not find prices for product: %d, brand: %d, date: %s",
                    pricesRequest.getProduct(),
                    pricesRequest.getBrand(),
                    pricesRequest.getDate().toString()));
        }
    }

    @Override
    public PricesResponse findPricesInditex(LocalDateTime dateAsOn, Long idProduct, Long idList) {
        //Implementación en caso de que los parámetros no vengan en un RequestBody
        PricesEntity pricesEntity;
        try{
            /*Busqueda en base de datos de la informacion de prices solicitada
             * y conversión al object DTO Prices para tratamiento interno
             * */
            pricesEntity= pricesRepository.findByBrandIdAndProductIdAndDate(
                    idList,
                    idProduct,
                    dateAsOn);
        }catch (Exception e){
            logger.error("Error translating to DTO from database");
            throw new TraductionDtoException(String.format("Error traduciendo los datos de la base de datos: %s",
                    e.getMessage()));
        }
        if (null != pricesEntity) {
            /*Se comprueba que haya encontrado algún precio, si no, se devuelve la excepción con
             * código 404 not found.
             * Si lo encuentra, se convierte en el formato de respuesta correspondiente
             */
            try{
                Prices prices = priceEntityMapper.mapPricesEntityToPrices(pricesEntity);
                return buildResponse(prices);
            } catch (Exception e){
                logger.error("Error translating to DTO from working object to response");
                throw new TraductionDtoException(String.format("Error traduciendo datos: %s",
                        pricesEntity));
            }
        } else {
            logger.error("No prices found in database");
            throw new PriceNotFoundException(String.format("Could not find prices for product: %d, brand: %d, date: %s",
                   idProduct,
                   idList,
                  dateAsOn.toString()));
        }
    }

    @Override
    public PricesResponse buildResponse(Prices prices) {
        //Se traduce formato DTO de base de datos a ResponseBody
        return pricesResponseMapper.mapPricesToPricesResponse(prices);
    }
}
