package com.piseth.java.school.phoneshop_night.mapper;

import com.piseth.java.school.phoneshop_night.dto.ModelDTO;
import com.piseth.java.school.phoneshop_night.entity.Model;
import com.piseth.java.school.phoneshop_night.service.BrandService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.Banner;

@Mapper(componentModel = "spring", uses = {BrandService.class})
public interface ModelMapper {
    // throw from DTO from controller to model
    ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);

    @Mapping(target = "brand", source = "brandId")
    Model toModel(ModelDTO dto);

    @Mapping(target = "brandId", source = "brand.id")
    ModelDTO toModelDTO(Model model);

}
