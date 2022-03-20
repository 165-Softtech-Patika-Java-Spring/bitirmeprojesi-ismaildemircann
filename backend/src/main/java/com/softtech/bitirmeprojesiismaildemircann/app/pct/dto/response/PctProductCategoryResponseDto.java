package com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response;

import com.softtech.bitirmeprojesiismaildemircann.app.pct.enums.PctProductType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PctProductCategoryResponseDto {

    private Long id;
    private PctProductType productType;
    private Integer vatRate;
}
