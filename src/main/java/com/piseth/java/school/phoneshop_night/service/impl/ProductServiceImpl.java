package com.piseth.java.school.phoneshop_night.service.impl;

import com.piseth.java.school.phoneshop_night.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop_night.dto.ProductSoldDTO;
import com.piseth.java.school.phoneshop_night.dto.SaleDTO;
import com.piseth.java.school.phoneshop_night.entity.Product;
import com.piseth.java.school.phoneshop_night.entity.ProductImportHistory;
import com.piseth.java.school.phoneshop_night.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop_night.mapper.ProductMapper;
import com.piseth.java.school.phoneshop_night.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshop_night.repository.ProductRepository;
import com.piseth.java.school.phoneshop_night.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductImportHistoryRepository importHistoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(Product product) {
        String name = "%s %s"
                .formatted(product.getModel().getName(), product.getColor().getName()) ;
        product.setName(name);
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", id));
    }

    @Override
    public void importProduct(ProductImportDTO importDTO) {
        Product product = getById(importDTO.getProductId());
        Integer availableUnit = 0;
        if(product.getAvailableUnit() != null) {
            availableUnit = product.getAvailableUnit();
        }
        product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
        productRepository.save(product);

        // save import product history
        ProductImportHistory importHistory = productMapper.toProductImportHistory(importDTO, product);
        importHistoryRepository.save(importHistory);
    }

    @Override
    public void setSalePrice(Long ProductId, BigDecimal price) {
        Product product = getById(ProductId);
        product.setSalePrice(price);
        productRepository.save(product);
    }

    @Override
    public void sale(SaleDTO saleDTO) {

    }


    @Override
    public void validateStock(Long productId, Integer numberOfUnit) {

    }

}