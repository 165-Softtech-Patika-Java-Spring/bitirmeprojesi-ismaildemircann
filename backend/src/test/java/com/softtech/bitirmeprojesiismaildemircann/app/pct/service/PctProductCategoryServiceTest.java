package com.softtech.bitirmeprojesiismaildemircann.app.pct.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.GenErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.ItemNotFoundException;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.PctProductCategory;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.service.entityservice.PctProductCategoryEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PctProductCategoryServiceTest {

    @Mock
    private PctProductCategoryEntityService pctProductCategoryEntityService;

    @InjectMocks
    private PctProductCategoryService pctProductCategoryService;

    @Test
    void shouldGetVatRate() {

        Integer vatRate = 8;
        PctProductCategory pctProductCategory = mock(PctProductCategory.class);

        when(pctProductCategoryEntityService.getByIdWithControl(anyLong())).thenReturn(pctProductCategory);
        when(pctProductCategory.getVatRate()).thenReturn(vatRate);

        Integer result = pctProductCategoryService.getVatRate(anyLong());

        assertEquals(vatRate, result);

        verify(pctProductCategoryEntityService).getByIdWithControl(anyLong());
    }

    @Test
    void shouldNotGetVatRateWhenCategoryDoesNotExist() {


        when(pctProductCategoryEntityService.getByIdWithControl(anyLong())).thenThrow(new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND));

        assertThrows(ItemNotFoundException.class, () -> pctProductCategoryService.getVatRate(anyLong()));
    }

    @Test
    void shouldFindAllProductCategories() {

        PctProductCategory pctProductCategory = mock(PctProductCategory.class);
        List<PctProductCategory> pctProductCategoryList = new ArrayList<>();
        pctProductCategoryList.add(pctProductCategory);

        PctProductCategoryResponseDto pctProductCategoryResponseDto = mock(PctProductCategoryResponseDto.class);
        List<PctProductCategoryResponseDto>  pctProductCategoryResponseDtoList = new ArrayList<>();
        pctProductCategoryResponseDtoList.add(pctProductCategoryResponseDto);

        when(pctProductCategoryEntityService.findAll()).thenReturn(pctProductCategoryList);

        List<PctProductCategoryResponseDto> result = pctProductCategoryService.findAllProductCategories();

        assertEquals(1, result.size());
    }

    @Test
    void shouldFindAllProductCategoriesWhenProductCategoryListIsEmpty() {

        List<PctProductCategory> pctProductCategoryList = new ArrayList<>();
        List<PctProductCategoryResponseDto> pctProductCategoryResponseDtoList = new ArrayList<>();

        when(pctProductCategoryEntityService.findAll()).thenReturn(pctProductCategoryList);

        List<PctProductCategoryResponseDto> result = pctProductCategoryService.findAllProductCategories();

        assertEquals(pctProductCategoryResponseDtoList.size(), result.size());
    }

    @Test
    void shouldFindAllWhenCustomerListIsNull() {

        when(pctProductCategoryEntityService.findAll()).thenThrow(new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND));

        assertThrows(ItemNotFoundException.class, () -> pctProductCategoryService.findAllProductCategories());

    }
}
