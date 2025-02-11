package com.assessment.receipt_processor.models.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data

public class Receipt {
    @NotNull
    private String retailer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate purchaseDate;

    @JsonFormat(pattern = "HH:mm")
    @NotNull
    private LocalTime purchaseTime;

    @NotNull
    private BigDecimal total;
    @NotEmpty(message = "Receipt must have at least one item")
    private List<Item> items;
}
