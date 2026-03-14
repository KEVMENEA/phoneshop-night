package com.piseth.java.school.phoneshop_night.controller;

import com.piseth.java.school.phoneshop_night.dto.SaleDTO;
import com.piseth.java.school.phoneshop_night.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/sales")
public class SaleController {
    private final SaleService saleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody SaleDTO saleDTO) {
        saleService.sale(saleDTO);
        return ResponseEntity.ok().build();
    }
    @PutMapping("{saleId}/cancelSale")
    public ResponseEntity<?> cancel(@PathVariable Long saleId) {
        saleService.cancelSale(saleId);
        return ResponseEntity.ok().build();
    }
}
