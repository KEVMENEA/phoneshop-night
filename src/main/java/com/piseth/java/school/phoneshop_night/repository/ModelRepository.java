package com.piseth.java.school.phoneshop_night.repository;

import com.piseth.java.school.phoneshop_night.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ModelRepository extends JpaRepository<Model, Integer>
{

    List<Model> findByBrandId(Integer BrandId);
}

