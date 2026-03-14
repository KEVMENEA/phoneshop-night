package com.piseth.java.school.phoneshop_night.controller;

import com.piseth.java.school.phoneshop_night.dto.PriceDTO;
import com.piseth.java.school.phoneshop_night.dto.ProductDTO;
import com.piseth.java.school.phoneshop_night.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop_night.entity.Product;
import com.piseth.java.school.phoneshop_night.mapper.ProductMapper;
import com.piseth.java.school.phoneshop_night.service.ProductService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.toProduct(productDTO);
        product = productService.create(product);

        return ResponseEntity.ok(product);
    }
    // @Valid is used to trigger validation for the request body object
    @PostMapping("importProduct")
    public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO importDTO){
        productService.importProduct(importDTO);
        return ResponseEntity.ok(importDTO);
    }
    @PostMapping("{productId}/setSalePrice")
    public ResponseEntity<?> setSalePrice(@PathVariable Long productId, @RequestBody PriceDTO priceDTO){
        productService.setSalePrice(productId, priceDTO.getPrice());
        return ResponseEntity.ok().build();
    }


}
