package com.assessment.receipt_processor.models.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data

public class Receipt {
    private String retailer;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;

    private BigDecimal total;
    private List<Item> items;
}
