package com.piseth.java.school.phoneshop_night.service;

import com.piseth.java.school.phoneshop_night.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop_night.entity.Product;

public interface ProductService {
    Product create(Product product);

    Product getById(Long id);

    void importProduct(ProductImportDTO importDTO);
}
