package com.java.testinditex.service;

import com.java.testinditex.helper.PricesHelper;
import com.java.testinditex.model.Prices;
import com.java.testinditex.repository.PricesRepository;
import com.java.testinditex.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio principal para el manejo de los objetos Prices
 */
@Service
public class PricesService {
    @Autowired
    private PricesRepository pricesRepository;

    /**
     * Metodo que devuelve una tarifa a aplicar
     *
     * @param date      Fecha en la cual debe estar la tarifa
     * @param brandId   Id de la marca
     * @param productId Id del producto
     * @return Una tarifa a aplicar en caso de haber o un objeto vacio en caso contrario
     */
    public Prices get(String date, Integer brandId, Integer productId) {
        PricesHelper helper = new PricesHelper();
        List<Prices> pricesList = pricesRepository.find(date, brandId, productId);

        // Recogemos todas las tarifas que concuerden con la informacion enviada
        if (Utils.isEmpty(pricesList)) {
            return new Prices();
        }

        // Devolvemos la tarifa segun orden en caso de haber mas de una
        return helper.help(pricesList);
    }
}
