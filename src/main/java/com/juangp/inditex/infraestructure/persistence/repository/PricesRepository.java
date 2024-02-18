package com.juangp.inditex.infraestructure.persistence.repository;

import com.juangp.inditex.infraestructure.persistence.entity.PricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PricesRepository  extends JpaRepository<PricesEntity,Long> {
    @Query(value = "SELECT * " +
            "FROM PRICES " +
            "WHERE :date BETWEEN start_date AND end_date " +
            "AND product_id = :productId " +
            "AND brand_id = :brandId " +
            "ORDER BY priority DESC " +
            "FETCH FIRST 1 ROW ONLY", nativeQuery = true
    )
    Optional<PricesEntity> findByBrandIdAndProductIdAndDate(Long brandId, Long productId, LocalDateTime date);

}
