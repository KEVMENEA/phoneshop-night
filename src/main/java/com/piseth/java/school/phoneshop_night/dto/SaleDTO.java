package com.piseth.java.school.phoneshop_night.dto;

import com.piseth.java.school.phoneshop_night.entity.Product;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaleDTO{
    private List<ProductSoldDTO> products;
    private LocalDate saleDate;
}
