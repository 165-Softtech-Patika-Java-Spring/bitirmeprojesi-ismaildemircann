package com.softtech.bitirmeprojesiismaildemircann.app.prd.service.entityservice;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.service.BaseEntityService;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dao.PrdProductDao;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.enums.PrdErrorMessage;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PrdProductEntityService extends BaseEntityService<PrdProduct, PrdProductDao> {

    public PrdProductEntityService(PrdProductDao dao) {
        super(dao);
    }

    public List<PrdProduct> findAll(Integer page, Integer size) {

        List<PrdProduct> list = getDao().findAll(PageRequest.of(page, size)).toList();

        return list;
    }

    public List<PrdProduct> findAllByCategoryId(Long categoryId) {

        List<PrdProduct> prdProductList = getDao().findAllByProductCategoryId(categoryId);

        controlListIsNull(prdProductList);

        return prdProductList;
    }

    public List<PrdProduct> findAllByCategoryId(Long categoryId, Integer page, Integer size) {

        List<PrdProduct> prdProductList = getDao().findAllByProductCategoryId(categoryId, PageRequest.of(page, size));

        controlListIsNull(prdProductList);

        return prdProductList;
    }

    public List<PrdProduct> findAllProductsByPriceFilter(BigDecimal minPrice, BigDecimal maxPrice, Integer page, Integer size) {

        List<PrdProduct> prdProductList = getDao().findByLastPriceBetween(minPrice, maxPrice, PageRequest.of(page, size));

        controlListIsNull(prdProductList);

        return prdProductList;
    }

    private void controlListIsNull(List<PrdProduct> prdProductList) {
        if(prdProductList == null) {
            throw new GenBusinessException(PrdErrorMessage.PRODUCT_NOT_FOUND_FOR_THIS_SEARCH_CRITERIA);
        }
    }
}
