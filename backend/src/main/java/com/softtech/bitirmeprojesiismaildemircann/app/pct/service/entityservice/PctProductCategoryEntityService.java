package com.softtech.bitirmeprojesiismaildemircann.app.pct.service.entityservice;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.service.BaseEntityService;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dao.PctProductCategoryDao;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.PctProductCategory;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.result.PctProductCategoryDetailResult;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.enums.PctErrorMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PctProductCategoryEntityService extends BaseEntityService<PctProductCategory, PctProductCategoryDao> {

    public PctProductCategoryEntityService(PctProductCategoryDao dao) {
        super(dao);
    }

    public List<PctProductCategoryDetailResult> findAllProductCategoriesWithDetail() {

        List<PctProductCategoryDetailResult> pctProductCategoryDetailResultList = getDao().findAllProductCategoriesWithDetail();

        if(pctProductCategoryDetailResultList == null) {
            throw new GenBusinessException(PctErrorMessage.PRODUCT_CATEGORY_DETAIL_INFO_NOT_FOUND);
        }

        return  pctProductCategoryDetailResultList;
    }
}
