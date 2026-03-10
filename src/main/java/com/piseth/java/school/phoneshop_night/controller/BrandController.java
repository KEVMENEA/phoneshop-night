package com.piseth.java.school.phoneshop_night.controller;
import java.util.List;
import java.util.Map;

import com.piseth.java.school.phoneshop_night.dto.BrandDTO;

import com.piseth.java.school.phoneshop_night.dto.ModelDTO;
import com.piseth.java.school.phoneshop_night.dto.PageDTO;
import com.piseth.java.school.phoneshop_night.entity.Brand;
import com.piseth.java.school.phoneshop_night.entity.Model;
import com.piseth.java.school.phoneshop_night.mapper.BrandMapper;
import com.piseth.java.school.phoneshop_night.mapper.ModelMapper;
import com.piseth.java.school.phoneshop_night.service.BrandService;
import com.piseth.java.school.phoneshop_night.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/brands")
public class BrandController {

    private final BrandService brandService;
    private final ModelMapper modelMapper;
    private final ModelService modelService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);

        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Long brandId) {
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long brandId, @RequestBody BrandDTO brandDTO){
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        Brand updatedBrand = brandService.update(brandId, brand);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(updatedBrand));
    }

//    @GetMapping
//    public ResponseEntity<?> getBrands(){
//
//        List<BrandDTO> list = brandService.getBrands()
//                .stream()
//                .map(BrandMapper.INSTANCE::toBrandDTO)
//                .collect(Collectors.toList());
//
//
//        return ResponseEntity.ok(list);
//    }

    @GetMapping
    public  ResponseEntity<?> getBrands(@RequestParam Map<String, String> params) {
        Page<Brand> page = brandService.getBrands(params);
        PageDTO pageDTO = new PageDTO(page);
        return  ResponseEntity.ok(pageDTO);
    }

    @GetMapping("{id}/models")
    public ResponseEntity<?> getModels(@PathVariable("id") Long brandId) {
    List<Model> brands = modelService.getBrandById(brandId);
    List<ModelDTO> list = brands
            .stream()
            .map(modelMapper::toModelDTO)
            .toList();

        return ResponseEntity.ok(list);
    }
}