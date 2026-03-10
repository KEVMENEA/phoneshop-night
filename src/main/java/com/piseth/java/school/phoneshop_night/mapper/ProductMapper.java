package com.piseth.java.school.phoneshop_night.mapper;

import com.piseth.java.school.phoneshop_night.dto.ModelDTO;
import com.piseth.java.school.phoneshop_night.dto.ProductDTO;
import com.piseth.java.school.phoneshop_night.dto.ProductImportDTO;
import com.piseth.java.school.phoneshop_night.entity.Model;
import com.piseth.java.school.phoneshop_night.entity.Product;
import com.piseth.java.school.phoneshop_night.entity.ProductImportHistory;
import com.piseth.java.school.phoneshop_night.service.ColorService;
import com.piseth.java.school.phoneshop_night.service.ModelService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {ModelService.class, ColorService.class})
public interface ProductMapper {

    @Mapping(target = "model", source = "modelId")
    @Mapping(target = "color", source = "colorId")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "dateImport", source = "importDTO.importDate")
    @Mapping(target = "pricePerUnit", source = "importDTO.importPrice")
    @Mapping(target = "product", source = "product")
    @Mapping(target = "id", ignore = true)
    ProductImportHistory toProductImportHistory(ProductImportDTO importDTO, Product product);

}


