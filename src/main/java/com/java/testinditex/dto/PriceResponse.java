package com.java.testinditex.dto;

import java.math.BigDecimal;

public record PriceResponse(
        Integer productId,
        Integer brandId,
        Long priceList,
        String startDate,
        String endDate,
        BigDecimal price
) {
}