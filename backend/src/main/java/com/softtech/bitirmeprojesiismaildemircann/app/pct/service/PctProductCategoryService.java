package com.softtech.bitirmeprojesiismaildemircann.app.pct.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.converter.PctProductCategoryMapper;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryDetailResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.PctProductCategory;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.result.PctProductCategoryDetailResult;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.enums.PctErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.service.entityservice.PctProductCategoryEntityService;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.service.PrdProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PctProductCategoryService {

    private final PctProductCategoryEntityService pctProductCategoryEntityService;
    PrdProductService prdProductService;

    @Autowired
    public void setPctProductCategoryService(@Lazy PrdProductService prdProductService) {
        this.prdProductService = prdProductService;
    }

    public Integer getVatRate(Long categoryId) {

        PctProductCategory pctProductCategory = pctProductCategoryEntityService.getByIdWithControl(categoryId);

        return pctProductCategory.getVatRate();
    }

    public List<PctProductCategoryResponseDto> findAllProductCategories() {

        List<PctProductCategory> pctProductCategoryList = pctProductCategoryEntityService.findAll();

        return PctProductCategoryMapper.INSTANCE.convertToPctProductCategoryResponseDtoList(pctProductCategoryList);
    }

    public List<PctProductCategoryDetailResponseDto> findAllProductCategoriesWithDetail() {

        List<PctProductCategoryDetailResult> pctProductCategoryDetailResultList = pctProductCategoryEntityService.findAllProductCategoriesWithDetail();

        return PctProductCategoryMapper.INSTANCE.convertToPctProductCategoryDetailResultList(pctProductCategoryDetailResultList);
    }

    @Transactional
    public PctProductCategoryResponseDto updateVatRate(Long categoryId, Integer vatRate) {

        PctProductCategory pctProductCategory = pctProductCategoryEntityService.getByIdWithControl(categoryId);

        try {
            pctProductCategory.setVatRate(vatRate);
            pctProductCategory = pctProductCategoryEntityService.save(pctProductCategory);

            prdProductService.batchUpdateProductPrices(categoryId, vatRate);

            return PctProductCategoryMapper.INSTANCE.convertToPctProductCategoryResponseDto(pctProductCategory);

        } catch (Exception e) {
            throw new GenBusinessException(PctErrorMessage.PRODUCT_CATEGORY_UPDATE_VAT_RATE_ERROR);
        }
    }
}
