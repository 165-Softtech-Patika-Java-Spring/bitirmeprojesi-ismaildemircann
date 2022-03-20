package com.softtech.bitirmeprojesiismaildemircann.app.pct.dao;

import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.PctProductCategory;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.result.PctProductCategoryDetailResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PctProductCategoryDao extends JpaRepository<PctProductCategory, Long> {

    @Query("SELECT " +
            "pct.productType as productType, " +
            "pct.vatRate as vatRate, " +
            "min(prd.lastPrice) as minPrice, " +
            "max(prd.lastPrice) as maxPrice, " +
            "avg(prd.lastPrice) as avgPrice, " +
            "count(prd) as productCount " +
            "FROM PctProductCategory pct " +
            "LEFT OUTER JOIN PrdProduct prd " +
            "ON pct.id = prd.productCategoryId " +
            "GROUP BY pct.id")
    List<PctProductCategoryDetailResult> findAllProductCategoriesWithDetail();
}
