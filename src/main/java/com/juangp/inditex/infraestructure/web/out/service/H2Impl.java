package com.juangp.inditex.infraestructure.web.out.service;

import com.juangp.inditex.application.useCase.out.H2Database;
import com.juangp.inditex.domain.exception.PriceNotFoundException;
import com.juangp.inditex.domain.exception.TraductionDtoException;
import com.juangp.inditex.domain.model.prices.dto.Prices;
import com.juangp.inditex.infraestructure.persistence.entity.PricesEntity;
import com.juangp.inditex.infraestructure.persistence.mapping.PriceEntityMapper;
import com.juangp.inditex.infraestructure.persistence.repository.PricesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Qualifier("h2Prices")
public class H2Impl implements H2Database {

    @Autowired
    private PricesRepository pricesRepository;

    private final PriceEntityMapper priceEntityMapper=new PriceEntityMapper();
    private static final Logger logger = LoggerFactory.getLogger(H2Impl.class);

    @Override
    public Prices findByBrandIdAndProductIdAndDate(Long brandId, Long productId, LocalDateTime date) {
        /*Busqueda en base de datos de la informacion de prices solicitada
        * y conversión al object DTO Prices para tratamiento interno
        * */
        PricesEntity pricesEntity=pricesRepository.findByBrandIdAndProductIdAndDate(brandId,productId,date);
        if (null != pricesEntity) {
            /*Se comprueba que haya encontrado algún precio, si no, se devuelve la excepción con
             * código 404 not found.
             * Si lo encuentra, se convierte en el formato de respuesta correspondiente
             */
            try {
                return priceEntityMapper.mapPricesEntityToPrices(pricesEntity);
            } catch (Exception e) {
                logger.error("Error translating to DTO from database");
                throw new TraductionDtoException(String.format("Error traduciendo los datos de la base de datos: %s",
                        e.getMessage()));
            }
        }else{
            logger.error("No prices found in database");
            throw new PriceNotFoundException(String.format("Could not find prices for product: %d, brand: %d, date: %s",
                    productId,brandId,date));
        }
    }
}
