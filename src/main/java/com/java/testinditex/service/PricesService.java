package com.java.testinditex.service;

import com.java.testinditex.dto.PriceResponse;
import com.java.testinditex.exception.PriceNotFoundException;
import com.java.testinditex.mapper.PriceMapper;
import com.java.testinditex.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public PriceResponse get(String date, Integer brandId, Integer productId) {
        return pricesRepository.findApplicablePrice(date, productId, brandId)
                .map(PriceMapper::toResponse)
                .orElseThrow(() -> new PriceNotFoundException("No se encontró precio aplicable"));
    }
}
