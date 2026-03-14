package com.piseth.java.school.phoneshop_night.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "productImportHistories")
public class ProductImportHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "import_id")
    private long id;

    @Column(name = "date_import")
    private LocalDateTime dateImport;

    @Column(name = "import_unit")
    private Integer importUnit;

    @Column(name = "price_per_unit")
    private Double pricePerUnit;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
