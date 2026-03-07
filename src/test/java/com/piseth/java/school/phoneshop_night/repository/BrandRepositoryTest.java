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

        // given
        Brand brand = new Brand();
        brand.setName("Samsung");

//        Brand brand2 = new Brand();
//        brand2.setName("Apple");

        brandRepository.save(brand);
//        brandRepository.save(brand2);
        // when

        List<Brand> brandList = brandRepository.findByNameLike("%S%");
//        List<Brand> brandList2 = brandRepository.findByNameLike("%A%");
//        List<Brand> brandList2 = brandRepository.findByNameLike("%A%");

        // then
        assertEquals(1, brandList.size());
        assertEquals("Samsung", brandList.get(0).getName());
//        assertEquals("Apple", brandList.get(1).getName());
        brandRepository.save(brand);

    }


}
