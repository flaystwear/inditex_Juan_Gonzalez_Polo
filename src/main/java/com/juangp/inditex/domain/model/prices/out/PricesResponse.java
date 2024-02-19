package com.juangp.inditex.domain.model.prices.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.juangp.inditex.domain.model.prices.dto.FullPrice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PricesResponse {
    Long productId;
    Long brandId;
    Long priceList;
    LocalDateTime startDate;
    LocalDateTime endDate;
    FullPrice price;
}
