package com.softtech.bitirmeprojesiismaildemircann.app.prd.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.service.PctProductCategoryService;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.converter.PrdProductMapper;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.response.PrdProductResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.enums.PrdErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.service.entityservice.PrdProductEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrdProductService {

    private final PrdProductEntityService prdProductEntityService;
    private final PctProductCategoryService pctProductCategoryService;

    public PrdProductResponseDto saveProduct(PrdProductSaveRequestDto prdProductSaveRequestDto) {

        BigDecimal taxFreePrice = prdProductSaveRequestDto.getTaxFreePrice();

        Long productCategoryId = prdProductSaveRequestDto.getProductCategoryId();

        Integer vatRate = pctProductCategoryService.getVatRate(productCategoryId);
        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductSaveRequestDto);

        prdProduct = setProductPriceWithTax(prdProduct, taxFreePrice, vatRate);

        prdProduct = prdProductEntityService.save(prdProduct);

        PrdProductResponseDto prdProductResponseDto = PrdProductMapper.INSTANCE.convertToPrdProductResponseDto(prdProduct);;

        return prdProductResponseDto;

    }

    public List<PrdProductResponseDto> findAllProducts() {

        List<PrdProduct> prdProductList = prdProductEntityService.findAll();

        List<PrdProductResponseDto> prdProductResponseDtoList = PrdProductMapper.INSTANCE.convertToPrdProductResponseDtoList(prdProductList);

        return  prdProductResponseDtoList;
    }

    public List<PrdProductResponseDto> findAllProductsByCategory(Long categoryId) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllByCategoryId(categoryId);

        List<PrdProductResponseDto> prdProductResponseDtoList = PrdProductMapper.INSTANCE.convertToPrdProductResponseDtoList(prdProductList);

        return  prdProductResponseDtoList;
    }

    public List<PrdProductResponseDto> findAllProductsByPriceFilter(BigDecimal minPrice, BigDecimal maxPrice) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllProductsByPriceFilter(minPrice, maxPrice);

        List<PrdProductResponseDto> prdProductResponseDtoList = PrdProductMapper.INSTANCE.convertToPrdProductResponseDtoList(prdProductList);

        return prdProductResponseDtoList;
    }

    public void deleteProductById(Long productId) {

        prdProductEntityService.deleteById(productId);
    }

    public PrdProductResponseDto updateProduct(PrdProductUpdateRequestDto prdProductUpdateRequestDto) {

        Long productId = prdProductUpdateRequestDto.getId();
        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(productId);

        BigDecimal newTaxFreePrice = prdProductUpdateRequestDto.getTaxFreePrice();
        Long newCategoryId = prdProductUpdateRequestDto.getProductCategoryId();
        Integer vatRate = pctProductCategoryService.getVatRate(newCategoryId);

        prdProduct.setName(prdProductUpdateRequestDto.getName());
        prdProduct.setTaxFreePrice(newTaxFreePrice);
        prdProduct = setProductPriceWithTax(prdProduct, newTaxFreePrice, vatRate);
        prdProduct.setProductCategoryId(newCategoryId);

        prdProduct = prdProductEntityService.save(prdProduct);

        PrdProductResponseDto prdProductResponseDto = PrdProductMapper.INSTANCE.convertToPrdProductResponseDto(prdProduct);;

        return prdProductResponseDto;
    }

    public PrdProductResponseDto updateProductPrice(Long productId, BigDecimal newProductTaxFreePrice) {

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(productId);

        Integer vatRate = pctProductCategoryService.getVatRate(prdProduct.getProductCategoryId());

        prdProduct.setTaxFreePrice(newProductTaxFreePrice);
        prdProduct = setProductPriceWithTax(prdProduct, newProductTaxFreePrice, vatRate);

        prdProduct = prdProductEntityService.save(prdProduct);

        PrdProductResponseDto prdProductResponseDto = PrdProductMapper.INSTANCE.convertToPrdProductResponseDto(prdProduct);

        return prdProductResponseDto;
    }

    public void batchUpdateProductPrices(Long categoryId, Integer vatRate) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllByCategoryId(categoryId);

        for (PrdProduct product : prdProductList) {
            updatePrice(product, vatRate);
        }
    }

    private PrdProduct setProductPriceWithTax(PrdProduct prdProduct, BigDecimal taxFreePrice, Integer vatRate) {

        validatePrice(taxFreePrice);

        BigDecimal vatPrice = calculateVatPrice(taxFreePrice, vatRate);
        BigDecimal lastPrice = calculateLastPrice(taxFreePrice, vatPrice);
        prdProduct.setVatPrice(vatPrice);
        prdProduct.setLastPrice(lastPrice);

        return prdProduct;
    }

    private BigDecimal calculateLastPrice(BigDecimal taxFreePrice, BigDecimal vatPrice) {

        return taxFreePrice.add(vatPrice);
    }

    private BigDecimal calculateVatPrice(BigDecimal taxFreePrice, Integer vatRate) {

        BigDecimal vatPrice = taxFreePrice.multiply(BigDecimal.valueOf(vatRate)).divide(BigDecimal.valueOf(100));

        return vatPrice;
    }

    private void validatePrice(BigDecimal taxFreePrice) {

        if (taxFreePrice.compareTo(BigDecimal.ZERO) < 0) {
            throw new GenBusinessException(PrdErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO);
        }
    }

    private void updatePrice(PrdProduct prdProduct, Integer vatRate) {

        BigDecimal vatPrice = calculateVatPrice(prdProduct.getTaxFreePrice(), vatRate);
        BigDecimal lastPrice = calculateLastPrice(prdProduct.getTaxFreePrice(), vatPrice);
        prdProduct.setVatPrice(vatPrice);
        prdProduct.setLastPrice(lastPrice);

        prdProductEntityService.save(prdProduct);
    }
}
