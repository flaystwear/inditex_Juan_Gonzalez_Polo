package com.juangp.inditex.infraestructure.persistence.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="PRICES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PricesEntity implements Serializable {
    @Id
    @Column(name = "id_price")
    Long idPrice;

    @Column(name = "brand_id")
    Long brandId;

    @Column(name = "start_date")
    LocalDateTime startDate;

    @Column(name = "end_date")
    LocalDateTime endDate;

    @Column(name = "price_list")
    Long priceList;

    @Column(name = "product_id")
    Long productId;

    @Column(name = "priority")
    Long priority;

    @Column(name = "price")
    BigDecimal price;

    @Column(name = "currency")
    String currency;
}
