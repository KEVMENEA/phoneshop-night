package com.piseth.java.school.phoneshop_night.service;

import com.piseth.java.school.phoneshop_night.entity.Brand;
import com.piseth.java.school.phoneshop_night.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop_night.repository.BrandRepository;
import com.piseth.java.school.phoneshop_night.service.impl.BrandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    private BrandServiceImpl brandService;

    @BeforeEach
    public void setUp() {
        brandService = new BrandServiceImpl(brandRepository);
    }

    @Test
    public void testCreate() {
        // given
        Brand brand = new Brand();
        brand.setName("Apple");

        // when
        brandService.create(brand);

        // then
        verify(brandRepository, times(1)).save(brand);
        //verify(brandRepository, times(1)).delete(brand);
    }



    @Test
    public void testGetById() {
        // given
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Apple");

        // when
        when(brandRepository.findById(1L)).thenReturn(Optional.of(brand));
        Brand result = brandService.getById(1L);

        // then
        assertEquals(1, result.getId());
        assertEquals("Apple", result.getName());


    }

    @Test
    public void testGetByIdThrow() {
        // given
        // when
        when(brandRepository.findById(2L)).thenReturn(Optional.empty());
        // then
        assertThatThrownBy(() -> brandService.getById(2L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("Brand with id = 2 not found");

    }
}
