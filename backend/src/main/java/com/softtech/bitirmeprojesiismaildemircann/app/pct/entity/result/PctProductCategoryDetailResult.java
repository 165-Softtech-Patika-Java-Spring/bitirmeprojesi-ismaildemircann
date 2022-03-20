package com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.result;

import com.softtech.bitirmeprojesiismaildemircann.app.pct.enums.PctProductType;

import java.math.BigDecimal;

public interface PctProductCategoryDetailResult {

    PctProductType getProductType();
    Integer getVatRate();
    BigDecimal getAvgPrice();
    BigDecimal getMinPrice();
    BigDecimal getMaxPrice();
    BigDecimal getProductCount();
}
