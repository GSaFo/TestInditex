package com.java.testinditex.dto;

public record ErrorResponse(
        String code,
        String message
) {}