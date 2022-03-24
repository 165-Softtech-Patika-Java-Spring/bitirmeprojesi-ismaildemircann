package com.softtech.bitirmeprojesiismaildemircann.app.pct.service.entityservice;

import com.softtech.bitirmeprojesiismaildemircann.app.pct.dao.PctProductCategoryDao;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.PctProductCategory;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.result.PctProductCategoryDetailResult;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dao.PrdProductDao;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.service.entityservice.PrdProductEntityService;
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
public class PctProductCategoryEntityServiceTest {

    @Mock
    private PctProductCategoryDao pctProductCategoryDao;

    @InjectMocks
    private PctProductCategoryEntityService pctProductCategoryEntityService;

    @Test
    void shouldFindAllProductCategoriesWithDetail() {

        PctProductCategoryDetailResult pctProductCategoryDetailResult = mock(PctProductCategoryDetailResult.class);
        List<PctProductCategoryDetailResult> pctProductCategoryDetailResultList = new ArrayList<>();
        pctProductCategoryDetailResultList.add(pctProductCategoryDetailResult);

        when(pctProductCategoryDao.findAllProductCategoriesWithDetail()).thenReturn(pctProductCategoryDetailResultList);

        List<PctProductCategoryDetailResult> result = pctProductCategoryEntityService.findAllProductCategoriesWithDetail();

        assertEquals(1, result.size());
    }
}
