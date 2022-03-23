package com.softtech.bitirmeprojesiismaildemircann.app.prd.controller;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.response.PrdProductResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.service.PrdProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(
            tags = "Product", description = "This method save a product.", summary = "Creates new product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PrdProductSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"name\": \"Apple\",\"taxFreePrice\": \"18.50\",\"productCategoryId\": \"1\"}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PostMapping
    @Validated
    public ResponseEntity saveProduct(@RequestBody @Valid PrdProductSaveRequestDto prdProductSaveRequestDto) {

        PrdProductResponseDto prdProductResponseDto = prdProductService.saveProduct(prdProductSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDto));
    }

    @Operation(tags = "Product", description = "This method returns products by page and size")
    @GetMapping
    public ResponseEntity findAllProducts(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "30") Integer size
    ) {

        List<PrdProductResponseDto> prdProductResponseDtoList = prdProductService.findAllProducts(page, size);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDtoList));
    }

    @Operation(tags = "Product", description = "This method returns products by page and size, by given category id")
    @GetMapping("{categoryId}")
    public ResponseEntity findAllProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "30") Integer size
    ) {

        List<PrdProductResponseDto> prdProductResponseDtoList = prdProductService.findAllProductsByCategory(categoryId, page, size);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDtoList));
    }

    @Operation(tags = "Product", description = "This method will return products by page and size with given price range")
    @GetMapping("price")
    public ResponseEntity findAllProductsByPriceFilter(
            @Min(0) @RequestParam(value = "minPrice") BigDecimal minPrice,
            @Min(0) @RequestParam(value = "maxPrice") BigDecimal maxPrice,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "30") Integer size
    ) {

        List<PrdProductResponseDto> prdProductResponseDtoList = prdProductService.findAllProductsByPriceFilter(minPrice, maxPrice, page, size);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDtoList));
    }

    @Operation(tags = "Product", description = "This method deletes a product whose id is given.")
    @DeleteMapping("{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {

        prdProductService.deleteProductById(productId);

        return ResponseEntity.ok(RestResponse.empty());

    }

    @Operation(
            tags = "Product", description = "This method updates a product's information.",summary = "Updates product",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = PrdProductUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"id\": \"1\",\"name\": \"Phone\",\"taxFreePrice\": \"4500\",\"productCategoryId\": \"5\"}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PutMapping()
    @Validated
    public ResponseEntity updateProduct(@RequestBody @Valid PrdProductUpdateRequestDto prdProductUpdateRequestDto) {

        PrdProductResponseDto prdProductResponseDto = prdProductService.updateProduct(prdProductUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDto));

    }

    @Operation(tags = "Product", description = "This method updates a product's price whose id is given.")
    @PatchMapping("{id}/{newProductPrice}")
    public ResponseEntity updateProductPrice(Long productId, BigDecimal newProductTaxFreePrice) {

        PrdProductResponseDto prdProductResponseDto = prdProductService.updateProductPrice(productId, newProductTaxFreePrice);

        return ResponseEntity.ok(RestResponse.of(prdProductResponseDto));
    }
}
