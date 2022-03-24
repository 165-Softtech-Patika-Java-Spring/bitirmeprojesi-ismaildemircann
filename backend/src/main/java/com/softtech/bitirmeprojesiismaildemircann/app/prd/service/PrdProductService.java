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

    /**
     * @param prdProductSaveRequestDto {name, taxFreePrice, productCategoryId}
     * @return This method returns the updated product information.
     */
    public PrdProductResponseDto saveProduct(PrdProductSaveRequestDto prdProductSaveRequestDto) {

        BigDecimal taxFreePrice = prdProductSaveRequestDto.getTaxFreePrice();
        Long productCategoryId = prdProductSaveRequestDto.getProductCategoryId();

        Integer vatRate = pctProductCategoryService.getVatRate(productCategoryId);
        PrdProduct prdProduct = PrdProductMapper.INSTANCE.convertToPrdProduct(prdProductSaveRequestDto);

        setProductPriceWithTax(prdProduct, taxFreePrice, vatRate);

        prdProduct = prdProductEntityService.save(prdProduct);

        return PrdProductMapper.INSTANCE.convertToPrdProductResponseDto(prdProduct);

    }

    /**
     * @param page Page number
     * @param size Page size
     * @return This method returns product list.
     */
    public List<PrdProductResponseDto> findAllProducts(Integer page, Integer size) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAll(page, size);

        return PrdProductMapper.INSTANCE.convertToPrdProductResponseDtoList(prdProductList);
    }

    /**
     * @param categoryId Category's id
     * @param page Page number
     * @param size Page size
     * @return This method returns product list by given category id.
     */
    public List<PrdProductResponseDto> findAllProductsByCategory(Long categoryId, Integer page, Integer size) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllByCategoryId(categoryId, page, size);

        return PrdProductMapper.INSTANCE.convertToPrdProductResponseDtoList(prdProductList);
    }

    /**
     * @param minPrice Minunum price
     * @param maxPrice Maximum price
     * @param page Page number
     * @param size Page size
     * @return This method returns product list by given price range.
     */
    public List<PrdProductResponseDto> findAllProductsByPriceFilter(BigDecimal minPrice, BigDecimal maxPrice, Integer page, Integer size) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllProductsByPriceFilter(minPrice, maxPrice, page, size);

        return PrdProductMapper.INSTANCE.convertToPrdProductResponseDtoList(prdProductList);
    }

    /**
     * @param productId Product's id
     */
    public void deleteProductById(Long productId) {

        prdProductEntityService.deleteById(productId);
    }

    /**
     * @param prdProductUpdateRequestDto {id, name, taxFreePrice, productCategoryId}
     * @return This method returns the updated product information.
     */
    public PrdProductResponseDto updateProduct(PrdProductUpdateRequestDto prdProductUpdateRequestDto) {

        Long productId = prdProductUpdateRequestDto.getId();
        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(productId);

        BigDecimal newTaxFreePrice = prdProductUpdateRequestDto.getTaxFreePrice();
        Long newCategoryId = prdProductUpdateRequestDto.getProductCategoryId();
        Integer vatRate = pctProductCategoryService.getVatRate(newCategoryId);

        prdProduct.setName(prdProductUpdateRequestDto.getName());
        prdProduct.setTaxFreePrice(newTaxFreePrice);
        setProductPriceWithTax(prdProduct, newTaxFreePrice, vatRate);
        prdProduct.setProductCategoryId(newCategoryId);

        prdProduct = prdProductEntityService.save(prdProduct);

        return PrdProductMapper.INSTANCE.convertToPrdProductResponseDto(prdProduct);
    }

    /**
     * @param productId Product's id
     * @param newProductTaxFreePrice New price of the product
     * @return This method returns the updated product information.
     */
    public PrdProductResponseDto updateProductPrice(Long productId, BigDecimal newProductTaxFreePrice) {

        PrdProduct prdProduct = prdProductEntityService.getByIdWithControl(productId);

        Integer vatRate = pctProductCategoryService.getVatRate(prdProduct.getProductCategoryId());

        prdProduct.setTaxFreePrice(newProductTaxFreePrice);
        setProductPriceWithTax(prdProduct, newProductTaxFreePrice, vatRate);

        prdProduct = prdProductEntityService.save(prdProduct);

        return PrdProductMapper.INSTANCE.convertToPrdProductResponseDto(prdProduct);
    }

    /**
     * This method updates the VAT prices and latest prices of all products in that category according to the given VAT rate.
     * @param categoryId Product category's id
     * @param vatRate Value-added tax of product category
     */
    public void batchUpdateProductPrices(Long categoryId, Integer vatRate) {

        List<PrdProduct> prdProductList = prdProductEntityService.findAllByCategoryId(categoryId);

        for (PrdProduct product : prdProductList) {
            updatePrice(product, vatRate);
        }
    }

    private void setProductPriceWithTax(PrdProduct prdProduct, BigDecimal taxFreePrice, Integer vatRate) {

        validatePrice(taxFreePrice);

        BigDecimal vatPrice = calculateVatPrice(taxFreePrice, vatRate);
        BigDecimal lastPrice = calculateLastPrice(taxFreePrice, vatPrice);
        prdProduct.setVatPrice(vatPrice);
        prdProduct.setLastPrice(lastPrice);

    }

    private BigDecimal calculateLastPrice(BigDecimal taxFreePrice, BigDecimal vatPrice) {

        return taxFreePrice.add(vatPrice);
    }

    private BigDecimal calculateVatPrice(BigDecimal taxFreePrice, Integer vatRate) {

        return taxFreePrice.multiply(BigDecimal.valueOf(vatRate)).divide(BigDecimal.valueOf(100));
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
