package com.piseth.java.school.phoneshop_night.repository;

import com.piseth.java.school.phoneshop_night.entity.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BrandRepositoryTest {

    @Autowired
    private BrandRepository brandRepository;

    @Test
    public void findByNameLike() {
        Brand brand = new Brand();
        brand.setName("Samsung");
        brandRepository.save(brand);

        List<Brand> brandList = brandRepository.findByNameLike("%S%");

        assertEquals(1, brandList.size());
        assertEquals("Samsung", brandList.get(0).getName());
    }
}