package com.java.testinditex.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.java.testinditex.adapter.LocalDateTimeTypeAdapter;
import com.java.testinditex.helper.PricesHelper;
import com.java.testinditex.model.Prices;
import com.java.testinditex.model.PricesDTO;
import com.java.testinditex.service.PricesService;
import com.java.testinditex.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * Controlador principal de las llamadas a la api
 */
@RestController
@RequestMapping("/prices") //localhost:8080/prices
public class PricesController {

    @Autowired
    private PricesService pricesService;

    private PricesHelper helper = new PricesHelper();

    /**
     * Endpoint principal
     *
     * @param date      Fecha en la cual tiene que estar la tarifa
     * @param brandId   Id de la marca
     * @param productId Id del producto
     * @return Un 200 con un JSON del objeto encontrado o en caso de no encontrarlo un 204 vacio.
     */
    @GetMapping("getPrice")
    public ResponseEntity<String> get(@RequestParam String date, @RequestParam Integer brandId, @RequestParam Integer productId) {
        // Comprobamos parametros de entrada
        if (!Utils.isValidDate(date) || brandId < 0 || productId < 0) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Parameter data is not valid");
        }

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).create();
        Prices prices = pricesService.get(date, brandId, productId);

        // Comprobamos si nos ha llegado un prices veridico o uno vacio. En caso de estar vacio es que no lo ha encontrado
        if (prices.getId() == null) {
            return ResponseEntity.noContent().build();
        }

        PricesDTO pricesDTO = helper.transformToDTO(prices);

        return ResponseEntity.ok(gson.toJson(pricesDTO));
    }
}
