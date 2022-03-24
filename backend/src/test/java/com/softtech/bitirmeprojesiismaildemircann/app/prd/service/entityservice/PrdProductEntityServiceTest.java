package com.softtech.bitirmeprojesiismaildemircann.app.prd.service.entityservice;

import com.softtech.bitirmeprojesiismaildemircann.app.prd.dao.PrdProductDao;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PrdProductEntityServiceTest {

    @Mock
    private PrdProductDao prdProductDao;

    @InjectMocks
    private PrdProductEntityService prdProductEntityService;

    @Test
    void shouldFindAll() {

        PrdProduct prdProduct = mock(PrdProduct.class);
        List<PrdProduct> prdProductList = new ArrayList<>();
        prdProductList.add(prdProduct);

        int page = 0;
        int size = 30;

        Pageable paging = PageRequest.of(page, size);

        Page<PrdProduct> prdProductPage = new PageImpl<>(prdProductList, paging, prdProductList.size());

        when(prdProductDao.findAll(paging)).thenReturn(prdProductPage);

        List<PrdProduct> result = prdProductEntityService.findAll(page, size);

        assertEquals(1, result.size());
    }

    @Test
    void shouldFindAllByCategoryId() {

        PrdProduct prdProduct = mock(PrdProduct.class);
        List<PrdProduct> prdProductList = new ArrayList<>();
        prdProductList.add(prdProduct);

        Long categoryId = 1L;

        when(prdProductDao.findAllByProductCategoryId(categoryId)).thenReturn(prdProductList);

        List<PrdProduct> result = prdProductEntityService.findAllByCategoryId(categoryId);

        assertEquals(1, result.size());
    }

    @Test
    void shouldFindAllByCategoryIdWithPageable() {

        PrdProduct prdProduct = mock(PrdProduct.class);
        List<PrdProduct> prdProductList = new ArrayList<>();
        prdProductList.add(prdProduct);

        Long categoryId = 1L;
        int page = 0;
        int size = 30;

        Pageable paging = PageRequest.of(page, size);

        when(prdProductDao.findAllByProductCategoryId(categoryId, paging)).thenReturn(prdProductList);

        List<PrdProduct> result = prdProductEntityService.findAllByCategoryId(categoryId, page, size);

        assertEquals(1, result.size());
    }

    @Test
    void shouldFindAllProductsByPriceFilter() {

        PrdProduct prdProduct = mock(PrdProduct.class);
        List<PrdProduct> prdProductList = new ArrayList<>();
        prdProductList.add(prdProduct);

        int page = 0;
        int size = 30;
        BigDecimal minPrice = BigDecimal.ONE;
        BigDecimal maxPrice = BigDecimal.ONE;

        Pageable paging = PageRequest.of(page, size);

        when(prdProductDao.findByLastPriceBetween(minPrice, maxPrice, paging)).thenReturn(prdProductList);

        List<PrdProduct> result = prdProductEntityService.findAllProductsByPriceFilter(minPrice, maxPrice, page, size);

        assertEquals(1, result.size());
    }
}
