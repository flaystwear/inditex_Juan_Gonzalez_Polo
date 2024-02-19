package com.juangp.inditex.domain.model.prices.mapping;

import com.juangp.inditex.domain.model.prices.dto.FullPrice;
import com.juangp.inditex.domain.model.prices.dto.Prices;
import com.juangp.inditex.domain.model.prices.out.PricesResponse;
import org.springframework.stereotype.Service;

@Service
public class PricesResponseMapper {
    public PricesResponse mapPricesToPricesResponse(Prices entity) {
        FullPrice fullPrice=new FullPrice(entity.getCurrency(), entity.getPrice());
        return new PricesResponse(
                entity.getProductId(),
                entity.getBrandId(),
                entity.getPriceList(),
                entity.getStartDate(),
                entity.getEndDate(),
                fullPrice
        );
    }
}
