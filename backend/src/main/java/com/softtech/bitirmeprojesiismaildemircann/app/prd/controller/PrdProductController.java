package com.softtech.bitirmeprojesiismaildemircann.app.prd.controller;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.response.PrdProductResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.service.PrdProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products/")
@RequiredArgsConstructor
public class PrdProductController {

    private final PrdProductService prdProductService;

    @PostMapping
    @Validated
    public ResponseEntity saveProduct(@RequestBody @Valid PrdProductSaveRequestDto prdProductSaveRequestDto) {

        PrdProductResponseDto prdProductResponseDto = prdProductService.saveProduct(prdProductSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDto));
    }

    @GetMapping
    public ResponseEntity findAllProducts() {

        List<PrdProductResponseDto> prdProductResponseDtoList = prdProductService.findAllProducts();

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDtoList));
    }

    @GetMapping("{categoryId}")
    public ResponseEntity findAllProductsByCategory(@PathVariable Long categoryId) {

        List<PrdProductResponseDto> prdProductResponseDtoList = prdProductService.findAllProductsByCategory(categoryId);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDtoList));
    }

    @GetMapping("price")
    public ResponseEntity findAllProductsByPriceFilter(
            @Min(0) @RequestParam(value = "minPrice") BigDecimal minPrice,
            @Min(0) @RequestParam(value = "maxPrice") BigDecimal maxPrice) {

        List<PrdProductResponseDto> prdProductResponseDtoList = prdProductService.findAllProductsByPriceFilter(minPrice, maxPrice);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDtoList));
    }

    @DeleteMapping("{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {

        prdProductService.deleteProductById(productId);

        return ResponseEntity.ok(RestResponse.empty());

    }

    @PutMapping()
    @Validated
    public ResponseEntity updateProduct(@RequestBody @Valid PrdProductUpdateRequestDto prdProductUpdateRequestDto) {

        PrdProductResponseDto prdProductResponseDto = prdProductService.updateProduct(prdProductUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDto));

    }

    @PatchMapping("{id}/{newProductPrice}")
    public ResponseEntity updateProductPrice(Long productId, BigDecimal newProductTaxFreePrice) {

        PrdProductResponseDto prdProductResponseDto = prdProductService.updateProductPrice(productId, newProductTaxFreePrice);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDto));
    }
}
