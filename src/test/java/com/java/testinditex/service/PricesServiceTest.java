package com.java.testinditex.service;

import com.java.testinditex.dto.PriceResponse;
import com.java.testinditex.exception.PriceNotFoundException;
import com.java.testinditex.mapper.PriceMapper;
import com.java.testinditex.model.Prices;
import com.java.testinditex.repository.PricesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PricesServiceTest {

    @Mock
    private PricesRepository pricesRepository;

    @InjectMocks
    private PricesService pricesService;

    @Test
    public void testGet_PriceFound() {
        // Datos de prueba
        String date = "2023-10-01T10:00:00";
        Integer brandId = 1;
        Integer productId = 35455;
        Prices price = new Prices();
        price.setPrice(BigDecimal.valueOf(35.50));
        price.setBrandId(brandId);
        price.setProductId(productId);

        PriceResponse expectedResponse = PriceMapper.toResponse(price);

        // Simular el comportamiento del repositorio
        when(pricesRepository.findApplicablePrice(date, productId, brandId))
                .thenReturn(Optional.of(price));

        // Ejecutar el método a probar
        PriceResponse actualResponse = pricesService.get(date, brandId, productId);

        // Verificar el resultado
        assertEquals(expectedResponse, actualResponse);

        // Verificar que el repositorio fue llamado
        verify(pricesRepository).findApplicablePrice(date, productId, brandId);
    }

    @Test
    public void testGet_PriceNotFound() {
        // Datos de prueba
        String date = "2023-10-01T10:00:00";
        Integer brandId = 1;
        Integer productId = 35455;

        // Simular que el repositorio no encuentra un precio
        when(pricesRepository.findApplicablePrice(date, productId, brandId))
                .thenReturn(Optional.empty());

        // Ejecutar el método a probar y verificar la excepción
        PriceNotFoundException exception = assertThrows(PriceNotFoundException.class, () -> {
            pricesService.get(date, brandId, productId);
        });

        // Verificar el mensaje de la excepción
        assertEquals("No se encontró precio aplicable", exception.getMessage());

        // Verificar que el repositorio fue llamado
        verify(pricesRepository).findApplicablePrice(date, productId, brandId);
    }
}