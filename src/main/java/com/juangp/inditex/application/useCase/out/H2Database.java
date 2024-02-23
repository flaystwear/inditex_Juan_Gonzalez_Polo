package com.juangp.inditex.application.useCase.out;

import com.juangp.inditex.domain.model.prices.dto.Prices;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface H2Database {
    Prices findByBrandIdAndProductIdAndDate(Long productId, Long brandId, LocalDateTime date);
}
