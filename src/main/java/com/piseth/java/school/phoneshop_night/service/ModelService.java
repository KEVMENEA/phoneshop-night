package com.piseth.java.school.phoneshop_night.service;

import com.piseth.java.school.phoneshop_night.entity.Model;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface ModelService {
    Model save(Model model);
    List<Model> getByBrandId(Integer brandId);
}
