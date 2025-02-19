package com.java.testinditex.helper;

import com.java.testinditex.dto.PriceResponse;
import com.java.testinditex.mapper.MapperConfig;
import com.java.testinditex.model.Prices;

import java.util.List;

public class PricesHelper {

    private static MapperConfig mapperConfig = new MapperConfig();

    /**
     * Metodo que recibe una lista de tarifas y comprueba cual tiene mayor prioridad
     *
     * @return Una tarifa a aplicar
     */
    public static Prices help(List<Prices> pricesList) {
        Prices price = pricesList.get(0);
        for (Prices prices : pricesList) {
            if (price.getPriority() < prices.getPriority()) {
                price = prices;
            }
        }

        return price;
    }

    /**
     * Metodo para convertir un objeto Prices a un Prices para la API
     *
     * @param prices Un objeto prices
     * @return Un objeto simplificado
     */
    public static PriceResponse transformToDTO(Prices prices) {
        return mapperConfig.map(prices, PriceResponse.class);
    }
}
