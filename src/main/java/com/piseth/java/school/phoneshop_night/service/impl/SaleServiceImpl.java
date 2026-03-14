package com.piseth.java.school.phoneshop_night.service.impl;

import com.piseth.java.school.phoneshop_night.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop_night.dto.ProductSoldDTO;
import com.piseth.java.school.phoneshop_night.dto.SaleDTO;
import com.piseth.java.school.phoneshop_night.entity.Product;
import com.piseth.java.school.phoneshop_night.entity.ProductImportHistory;
import com.piseth.java.school.phoneshop_night.entity.Sale;
import com.piseth.java.school.phoneshop_night.entity.SaleDetail;
import com.piseth.java.school.phoneshop_night.exception.ApiException;
import com.piseth.java.school.phoneshop_night.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop_night.mapper.ProductMapper;
import com.piseth.java.school.phoneshop_night.repository.ProductImportHistoryRepository;
import com.piseth.java.school.phoneshop_night.repository.ProductRepository;
import com.piseth.java.school.phoneshop_night.repository.SaleDetailRepository;
import com.piseth.java.school.phoneshop_night.repository.SaleRepository;
import com.piseth.java.school.phoneshop_night.service.ProductService;
import com.piseth.java.school.phoneshop_night.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SaleServiceImpl implements SaleService {
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;


    //get all product IDs from request
    // check product exists
    // load products from database
    //4. check stock before sale
    //  5. create sale header
    // 6. save sold quantity and cut stock

    @Transactional
    @Override
    public void sale(SaleDTO saleDTO) {
        List<Long> productIds = saleDTO.getProducts().stream()
                .map(ProductSoldDTO::getProductId)
                .toList();

        productIds.forEach(productService::getById);

        List<Product> products = productRepository.findAllById(productIds);

        // Convert product list to map
        Map<Long, Product> productMap = products.stream()
                .collect(Collectors.toMap(Product::getId, Function.identity()));

        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());
            if (product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(
                        HttpStatus.BAD_REQUEST,
                        "Product [%s] is not enough in stock".formatted(product.getName())
                );
            }

            if (product.getSalePrice() == null) {
                throw new ApiException(
                        HttpStatus.BAD_REQUEST,
                        "Product [%s] has no sale price".formatted(product.getName())
                );
            }
        });

        Sale sale = new Sale();
        sale.setSoldDate(LocalDate.now());
        saleRepository.save(sale);

        saleDTO.getProducts().forEach(ps -> {
            Product product = productMap.get(ps.getProductId());

            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setSale(sale);
            saleDetail.setProduct(product);
            saleDetail.setUnit(ps.getNumberOfUnit());
            saleDetail.setAmount(
                    product.getSalePrice().multiply(BigDecimal.valueOf(ps.getNumberOfUnit()))
            );
            saleDetailRepository.save(saleDetail);

            product.setAvailableUnit(product.getAvailableUnit() - ps.getNumberOfUnit());
            productRepository.save(product);
        });
    }

    @Override
    public Sale getById(Long saleId) {
        return saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found", saleId));
    }

    @Override
    public void cancelSale(Long saleId) {
        // update sale status
        Sale sale = getById(saleId);
        sale.setActive(false);
        saleRepository.save(sale);

        // update stock
        List<SaleDetail> saleDetails = saleDetailRepository.findBySaleId(saleId);

        // Extract product IDs
        List<Long> productIds = saleDetails.stream()
                        .map(sd -> sd.getProduct().getId())
                        .toList();

        //   Load the products
        List<Product> products = productRepository.findAllById(productIds);
        // Convert products to a map
        Map<Long, Product> productMap = products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));

        saleDetails.forEach(sd -> {
            Product product = productMap.get(sd.getProduct().getId());
            product.setAvailableUnit(product.getAvailableUnit() + sd.getUnit());
            productRepository.save(product);
        });

    }


    private void saveSale(SaleDTO saleDTO) {
        Sale sale = new Sale();
        sale.setSoldDate(LocalDate.now());
        saleRepository.save(sale);

        // SaleDetail
        saleDTO.getProducts().forEach(ps ->{
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setAmount(null);
        });
    }
    private void validate(SaleDTO saleDTO) {
        saleDTO.getProducts().forEach(ps ->{
            Product product = productService.getById(ps.getProductId());
            if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
                throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock".formatted(product.getName()));
            }
        });
    }
}
