package com.juangp.inditex.infraestructure.web.in.contrroller;

import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.model.in.PricesRequest;
import com.juangp.inditex.domain.model.out.PricesResponse;
import com.juangp.inditex.infraestructure.web.in.service.PriceFinderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PricesController {
    private static final Logger logger = LoggerFactory.getLogger(PricesController.class);
    @Autowired
    PriceFinderImpl priceFinderImpl;

    @PostMapping("/prices")
    ResponseEntity<PricesResponse> findPrice(@RequestBody PricesRequest pricesRequest) throws PriceNotFoundException {
        logger.info("Petición recibida a las "+ LocalDateTime.now()
                +".\n Request: "
                +pricesRequest.toString()
        );
        PricesResponse response= priceFinderImpl.findPricesInditex(pricesRequest);
        logger.info("Petición respondida: "
            +response
        );
        return ResponseEntity.ok(response);
    }

}
