package com.softtech.bitirmeprojesiismaildemircann.app.pct.controller;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryDetailResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.service.PctProductCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products/categories")
@RequiredArgsConstructor
public class PctProductCategoryController {

    private final PctProductCategoryService pctProductCategoryService;

    @Operation(tags = "Product Category", description = "This method returns product categories.", summary = "Get all product categories")
    @GetMapping
    public ResponseEntity findAllProductCategories() {

        List<PctProductCategoryResponseDto> pctProductCategoryResponseDtoList = pctProductCategoryService.findAllProductCategories();

        return ResponseEntity.ok(RestResponse.of(pctProductCategoryResponseDtoList));
    }

    @Operation(tags = "Product Category", description = "This method returns product categories with detail", summary = "Get all product categories with detail")
    @GetMapping("/detail")
    public ResponseEntity findAllProductCategoriesWithDetail() {

        List<PctProductCategoryDetailResponseDto> pctProductCategoryResponseDtoList = pctProductCategoryService.findAllProductCategoriesWithDetail();

        return ResponseEntity.ok(RestResponse.of(pctProductCategoryResponseDtoList));
    }

    @Operation(tags = "Product Category", description = "This method updates a product category's VAT rate. The prices of all products belonging to this category id will be updated.",
            summary = "Updates product category's VAT rate"
    )
    @PatchMapping("{categoryId}")
    public ResponseEntity updateVatRate(
            @Parameter(name = "categoryId", in = ParameterIn.PATH, schema = @Schema(type = "number", example = "2"))
            @PathVariable Long categoryId,
            @Parameter(name = "vatRate", in = ParameterIn.QUERY, schema = @Schema(type = "number", example = "8"))
            @Min(1) @RequestParam Integer vatRate) {

        PctProductCategoryResponseDto pctProductCategoryResponseDto = pctProductCategoryService.updateVatRate(categoryId, vatRate);

        return ResponseEntity.ok(RestResponse.of(pctProductCategoryResponseDto));
    }
}
