package com.piseth.java.school.phoneshop_night.repository;

import com.piseth.java.school.phoneshop_night.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
