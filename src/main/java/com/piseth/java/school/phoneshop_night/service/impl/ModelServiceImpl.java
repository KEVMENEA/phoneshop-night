package com.piseth.java.school.phoneshop_night.service.impl;

import com.piseth.java.school.phoneshop_night.entity.Model;
import com.piseth.java.school.phoneshop_night.exception.ResourceNotFoundException;
import com.piseth.java.school.phoneshop_night.repository.ModelRepository;
import com.piseth.java.school.phoneshop_night.service.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    @Override
    public Model save(Model model) {
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getBrandById(Long id) {
        return modelRepository.findBrandById(id);
    }

    @Override
    public Model getById(Long id) {
        return modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model", id));
    }

    public static class SellSerivce {
    }
}
