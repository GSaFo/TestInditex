package com.java.testinditex.repository;

import com.java.testinditex.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la tabla prices
 */
@Repository
public interface PricesRepository extends JpaRepository<Prices, Integer> {

    @Query(value = "SELECT * FROM prices WHERE PARSEDATETIME(:date, 'yyyy-MM-dd-HH.mm.ss') BETWEEN PARSEDATETIME(start_date, 'yyyy-MM-dd-HH.mm.ss') AND PARSEDATETIME(end_date, 'yyyy-MM-dd-HH.mm.ss')  AND brand_id = :brandId AND product_id = :productId", nativeQuery = true)
    List<Prices> find(@Param("date") String date, @Param("brandId") Integer brandId, @Param("productId") Integer productId);
}
