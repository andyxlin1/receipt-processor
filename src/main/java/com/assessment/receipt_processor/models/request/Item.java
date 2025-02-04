package com.assessment.receipt_processor.models.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
public class Item {
    private String shortDescription;
    private BigDecimal price;
}