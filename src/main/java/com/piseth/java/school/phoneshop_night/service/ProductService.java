package com.piseth.java.school.phoneshop_night.service;

import com.piseth.java.school.phoneshop_night.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop_night.dto.SaleDTO;
import com.piseth.java.school.phoneshop_night.entity.Product;

import java.math.BigDecimal;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void importProduct(ProductImportDTO importDTO);
    void setSalePrice(Long productId, BigDecimal price);
    void sale(SaleDTO saleDTO);
    void validateStock(Long productId, Integer numberOfUnit);
}

