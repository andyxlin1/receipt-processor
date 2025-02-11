package com.assessment.receipt_processor.models.request;

import lombok.Data;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class Item {
    @NotNull
    private String shortDescription;

    @NotNull
    @Positive
    private BigDecimal price;
}