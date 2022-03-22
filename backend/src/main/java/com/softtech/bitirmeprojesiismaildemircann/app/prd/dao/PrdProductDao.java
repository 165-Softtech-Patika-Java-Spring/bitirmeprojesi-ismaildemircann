package com.softtech.bitirmeprojesiismaildemircann.app.prd.dao;

import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrdProductDao extends JpaRepository<PrdProduct, Long> {

    List<PrdProduct> findAllByProductCategoryId(Long categoryId);

    List<PrdProduct> findAllByProductCategoryId(Long categoryId, Pageable pageable);

    List<PrdProduct> findByLastPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}