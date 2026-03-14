package com.piseth.java.school.phoneshop_night.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "products", uniqueConstraints = @UniqueConstraint(columnNames = {"model_id", "color_id"}))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "available_unit")
    private Integer availableUnit;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @Column(name = "sale_price")
    @DecimalMin(value = "0.000001", message = "Price must be greater than 0")
    private BigDecimal salePrice;

}
