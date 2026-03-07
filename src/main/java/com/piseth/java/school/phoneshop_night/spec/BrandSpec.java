package com.piseth.java.school.phoneshop_night.spec;

import com.piseth.java.school.phoneshop_night.entity.Brand;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

@Data
public class BrandSpec implements Specification<Brand> {
    private final BrandFilter brandFilter;

    @Override
    public Predicate toPredicate(Root<Brand> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> list = new ArrayList<>();
        if (brandFilter.getId() != null) {
            Predicate id = root.get("id").in(brandFilter.getId());
            list.add(id);
        }
        if (brandFilter.getName() != null) {
            Predicate name = cb.like(cb.upper(root.get("name")), "%" + brandFilter.getName().toUpperCase() + "%");
            list.add(name);
        }
        return cb.and(list.toArray(new Predicate[0]));
    }
}
