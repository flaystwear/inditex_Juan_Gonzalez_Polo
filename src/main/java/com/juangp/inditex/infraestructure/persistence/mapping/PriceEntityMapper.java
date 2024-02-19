package com.juangp.inditex.infraestructure.persistence.mapping;

import com.juangp.inditex.domain.model.dto.Prices;
import com.juangp.inditex.infraestructure.persistence.entity.PricesEntity;
import org.springframework.stereotype.Service;

@Service
public class PriceEntityMapper {
    public Prices mapPricesEntityToPrices(PricesEntity entity) {
        return new Prices(
                entity.getIdPrice(),
                entity.getProductId(),
                entity.getBrandId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.getPriority(),
                entity.getPrice(),
                entity.getCurrency()
        );
    }
}
