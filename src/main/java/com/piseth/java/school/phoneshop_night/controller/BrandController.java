package com.piseth.java.school.phoneshop_night.controller;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.piseth.java.school.phoneshop_night.dto.BrandDTO;

import com.piseth.java.school.phoneshop_night.dto.PageDTO;
import com.piseth.java.school.phoneshop_night.entity.Brand;
import com.piseth.java.school.phoneshop_night.mapper.BrandMapper;
import com.piseth.java.school.phoneshop_night.service.BrandService;
import com.piseth.java.school.phoneshop_night.service.impl.BrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
        Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
        brand = brandService.create(brand);

        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId) {
        Brand brand = brandService.getById(brandId);
        return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDTO(brand));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer brandId, @RequestBody BrandDTO brandDTO){
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
}