package com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response;

import com.softtech.bitirmeprojesiismaildemircann.app.pct.enums.PctProductType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PctProductCategoryDetailResponseDto {

    private PctProductType productType;
    private Integer vatRate;
    private BigDecimal avgPrice;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private BigDecimal productCount;
}
