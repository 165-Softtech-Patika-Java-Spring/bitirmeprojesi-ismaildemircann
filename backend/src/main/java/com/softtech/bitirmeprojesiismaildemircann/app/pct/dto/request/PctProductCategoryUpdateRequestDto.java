package com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class PctProductCategoryUpdateRequestDto {

    @NotNull(message = "Id cannot be null!")
    private Long id;

    @NotNull(message = "Vat rate cannot be null!")
    @DecimalMin("0")
    private Integer varRate;
}
