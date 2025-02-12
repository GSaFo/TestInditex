package com.java.testinditex.dto;

import java.math.BigDecimal;

public record PriceResponse(
        Long productId,
        Integer brandId,
        Long priceList,
        String startDate,
        String endDate,
        BigDecimal price
) {
}