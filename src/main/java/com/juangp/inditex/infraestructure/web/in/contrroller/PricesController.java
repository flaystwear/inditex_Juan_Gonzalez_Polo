package com.juangp.inditex.infraestructure.web.in.contrroller;

import com.juangp.inditex.application.useCase.PriceFinder;
import com.juangp.inditex.infraestructure.web.service.ValidateRequestDataImpl;
import com.juangp.inditex.domain.model.prices.in.PricesRequest;
import com.juangp.inditex.domain.model.prices.out.PricesResponse;
import com.juangp.inditex.infraestructure.web.service.PriceFinderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class PricesController {
    private static final Logger logger = LoggerFactory.getLogger(PricesController.class);
    @Autowired
    @Qualifier("pricesFinder")
    PriceFinder priceFinderImpl;

    @Qualifier("validationPrices")
    ValidateRequestDataImpl validateRequestData= new ValidateRequestDataImpl();

    @PostMapping("/api/v2/prices")
    ResponseEntity<PricesResponse> findPrice(@RequestBody PricesRequest pricesRequest) {
        /*
        *Se coge el momento en el que se recibe la petición y lo convertimos en string
        * para mostrarlo en el log junto a la petición
        */
        LocalDateTime localDateTime1 = LocalDateTime.now();
        String formattedDateTime1 = localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("Petición recibida a  {}.",formattedDateTime1);
        //Se procede a comprobar que los parámetros estén correctos y a buscar el precio y generar la respuesta.
        validateRequestData.checkPricesRequest(pricesRequest);
        logger.info("Request: {}",pricesRequest);
        PricesResponse response= priceFinderImpl.findPricesInditex(pricesRequest);
        logger.info("Petición respondida : {}.", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //Endpoint de tipo get con variables recogidas de la url y por parámetro en vez de con un body: solución alternativa
    @GetMapping(value="/api/v2/prices/inditex/brand/{brandId}/product/{productId}")
    ResponseEntity<PricesResponse> findPriceAlternative(
            @PathVariable Long brandId,
            @PathVariable Long productId,
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        /*
         *Se coge el momento en el que se recibe la petición y lo convertimos en string
         * para mostrarlo en el log junto a la petición
         */
        LocalDateTime localDateTime1 = LocalDateTime.now();
        String formattedDateTime1 = localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logger.info("Petición recibida a  {}.",formattedDateTime1);
        //Se procede a comprobar que los parámetros estén correctos y a buscar el precio y generar la respuesta
        validateRequestData.checkPricesRequest(date, productId, brandId);
        logger.info("Request: brand={}, product={}, date={}",brandId,productId,date);
        PricesResponse response= priceFinderImpl.findPricesInditex(date, productId, brandId);
        logger.info("Petición respondida : {}.", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
