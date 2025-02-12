package com.java.testinditex.controller;

import com.java.testinditex.dto.PriceResponse;
import com.java.testinditex.service.PricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador principal de las llamadas a la api
 */
@RestController
@RequestMapping("/prices") //localhost:8080/prices
public class PricesController {

    @Autowired
    private PricesService pricesService;

    /**
     * Endpoint principal
     *
     * @param date      Fecha en la cual tiene que estar la tarifa
     * @param brandId   Id de la marca
     * @param productId Id del producto
     * @return Un 200 con un JSON del objeto encontrado o en caso de no encontrarlo un 204 vacio.
     */
    @GetMapping("getPrice")
    public ResponseEntity<PriceResponse> get(@RequestParam String date, @RequestParam Integer brandId, @RequestParam Integer productId) {
        return ResponseEntity.ok(pricesService.get(date, productId, brandId));
    }
}
