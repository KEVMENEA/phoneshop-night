package com.piseth.java.school.phoneshop_night.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long modelId;
    private Long colorId;

    @DecimalMin(value = "0.000001", message = "Price must be greater than 0")
    private BigDecimal salePrice;
}
