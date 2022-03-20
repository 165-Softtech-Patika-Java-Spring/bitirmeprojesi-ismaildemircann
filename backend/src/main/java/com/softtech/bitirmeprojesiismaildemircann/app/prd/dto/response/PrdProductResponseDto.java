package com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PrdProductResponseDto {

    private Long id;
    private String name;
    private BigDecimal taxFreePrice;
    private BigDecimal vatPrice;
    private BigDecimal lastPrice;
    private Long productCategoryId;
}
