package com.piseth.java.school.phoneshop_night.mapper;

import com.piseth.java.school.phoneshop_night.dto.BrandDTO;
import com.piseth.java.school.phoneshop_night.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    Brand toBrand(BrandDTO dto);

    BrandDTO toBrandDTO(Brand entity);

}
