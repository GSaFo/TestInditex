package com.java.testinditex.mapper;

import com.java.testinditex.dto.PriceResponse;
import com.java.testinditex.model.Prices;

public class PriceMapper {
    public static PriceResponse toResponse(Prices prices) {
        return new PriceResponse(
                prices.getProductId(),
                prices.getBrandId(),
                prices.getPriceList(),
                prices.getStartDate(),
                prices.getEndDate(),
                prices.getPrice()
        );
    }
}