package com.juangp.inditex.domain.model.dto;

import lombok.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Prices implements Serializable {
    Long idPrice;
    Long productId;
    Long brandId;
    Long priceList;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long priority;
    BigDecimal price;
    String currency;
}
