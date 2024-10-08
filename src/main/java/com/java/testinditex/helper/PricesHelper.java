package com.java.testinditex.helper;

import com.java.testinditex.mapper.MapperConfig;
import com.java.testinditex.model.Prices;
import com.java.testinditex.model.PricesDTO;

import java.util.List;

public class PricesHelper {

    private MapperConfig mapperConfig = new MapperConfig();

    /**
     * Metodo que recibe una lista de tarifas y comprueba cual tiene mayor prioridad
     *
     * @return Una tarifa a aplicar
     */
    public Prices help(List<Prices> pricesList) {
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
    public PricesDTO transformToDTO(Prices prices) {
        return mapperConfig.map(prices, PricesDTO.class);
    }
}
