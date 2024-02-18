package com.juangp.inditex.domain.model;

import lombok.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class Prices implements Serializable {
    Long idPrice;
    Long brandId;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Long priceList;
    Long productId;
    Long priority;
    BigDecimal price;
    String currency;
}
