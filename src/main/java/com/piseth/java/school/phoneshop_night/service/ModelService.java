package com.piseth.java.school.phoneshop_night.service;

import com.piseth.java.school.phoneshop_night.entity.Model;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getBrandById(Long brandId);
    Model getById(Long id);
}
