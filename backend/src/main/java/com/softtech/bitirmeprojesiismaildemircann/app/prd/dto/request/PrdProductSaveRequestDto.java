package com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PrdProductSaveRequestDto {

    @NotEmpty(message = "Name cannot be null or blank!")
    private String name;

    @NotNull(message = "Tax free price cannot be null!")
    @DecimalMin("0")
    private BigDecimal taxFreePrice;

    @NotNull(message = "Product category id cannot be null!")
    private Long productCategoryId;
}
