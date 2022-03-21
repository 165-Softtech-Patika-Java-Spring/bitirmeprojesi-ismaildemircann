package com.softtech.bitirmeprojesiismaildemircann.app.prd.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.GenErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.ItemNotFoundException;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.service.PctProductCategoryService;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.response.PrdProductResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.enums.PrdErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.service.entityservice.PrdProductEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrdProductServiceTest {

    @Mock
    private PrdProductEntityService prdProductEntityService;

    @Mock
    private PctProductCategoryService pctProductCategoryService;

    @InjectMocks
    private PrdProductService prdProductService;

    @Test
    void shouldSaveProduct() {
        PrdProductSaveRequestDto prdProductSaveRequestDto = mock(PrdProductSaveRequestDto.class);
        when(prdProductSaveRequestDto.getProductCategoryId()).thenReturn(1L);
        when(prdProductSaveRequestDto.getName()).thenReturn("testProduct");
        when(prdProductSaveRequestDto.getTaxFreePrice()).thenReturn(new BigDecimal("18.45"));

        when(pctProductCategoryService.getVatRate(anyLong())).thenReturn(8);

        PrdProduct prdProduct = mock(PrdProduct.class);
        when(prdProduct.getId()).thenReturn(1L);

        when(prdProductEntityService.save(any())).thenReturn(prdProduct);

        PrdProductResponseDto prdProductResponseDto = prdProductService.saveProduct(prdProductSaveRequestDto);

        assertEquals(1L, prdProductResponseDto.getId());

        verify(pctProductCategoryService).getVatRate(anyLong());
    }

    @Test
    void shouldNotSaveProductWhenCategoryDoesNotExist() {

        PrdProductSaveRequestDto prdProductSaveRequestDto = mock(PrdProductSaveRequestDto.class);
        when(prdProductSaveRequestDto.getProductCategoryId()).thenReturn(99L);

        when(pctProductCategoryService.getVatRate(anyLong())).thenThrow(new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND));

        assertThrows(ItemNotFoundException.class, () -> prdProductService.saveProduct(prdProductSaveRequestDto));
    }

    @Test
    void shouldNotSaveProductWhenPriceIsNotValid() {

        PrdProductSaveRequestDto prdProductSaveRequestDto = mock(PrdProductSaveRequestDto.class);
        when(prdProductSaveRequestDto.getProductCategoryId()).thenReturn(1L);
        when(prdProductSaveRequestDto.getName()).thenReturn("testProduct");
        when(prdProductSaveRequestDto.getTaxFreePrice()).thenReturn(new BigDecimal(-50));

        when(pctProductCategoryService.getVatRate(anyLong())).thenReturn(8);

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, ()
                -> prdProductService.saveProduct(prdProductSaveRequestDto));

        assertEquals(PrdErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO, genBusinessException.getBaseErrorMessage());

        verify(pctProductCategoryService).getVatRate(anyLong());
    }

    @Test
    void shouldDeleteProduct() {

        doNothing().when(prdProductEntityService).deleteById(anyLong());

        prdProductService.deleteProductById(anyLong());

        verify(prdProductEntityService).deleteById(anyLong());
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() {

        doThrow(new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND)).when(prdProductEntityService).deleteById(anyLong());

        ItemNotFoundException result = assertThrows(ItemNotFoundException.class, () -> prdProductService.deleteProductById(anyLong()));

        verify(prdProductEntityService).deleteById(anyLong());
        assertNotNull(result);
    }

    @Test
    void shouldUpdateProduct() {

        PrdProductUpdateRequestDto prdProductUpdateRequestDto = mock(PrdProductUpdateRequestDto.class);

        when(prdProductUpdateRequestDto.getName()).thenReturn("testProduct");
        when(prdProductUpdateRequestDto.getTaxFreePrice()).thenReturn(new BigDecimal("18.55"));
        when(prdProductUpdateRequestDto.getProductCategoryId()).thenReturn(4L);

        PrdProduct prdProduct = mock(PrdProduct.class);
        Long id = 18L;

        when(prdProduct.getId()).thenReturn(id);

        when(prdProductEntityService.save(any())).thenReturn(prdProduct);

        when(prdProductEntityService.getByIdWithControl(anyLong())).thenReturn(prdProduct);

        PrdProductResponseDto prdProductResponseDto = prdProductService.updateProduct(prdProductUpdateRequestDto);

        assertEquals(id, prdProductResponseDto.getId());

        verify(prdProductEntityService).getByIdWithControl(anyLong());
        verify(prdProductEntityService).save(any());
    }

    @Test
    void shouldNotUpdateProductWhenCategoryDoesNotExist() {

        PrdProductUpdateRequestDto prdProductUpdateRequestDto = mock(PrdProductUpdateRequestDto.class);
        when(prdProductUpdateRequestDto.getProductCategoryId()).thenReturn(99L);

        when(pctProductCategoryService.getVatRate(anyLong())).thenThrow(new ItemNotFoundException(GenErrorMessage.ITEM_NOT_FOUND));

        assertThrows(ItemNotFoundException.class, () -> prdProductService.updateProduct(prdProductUpdateRequestDto));
    }

    @Test
    void shouldNotUpdateProductWhenPriceIsNotValid() {

        PrdProductUpdateRequestDto prdProductUpdateRequestDto = mock(PrdProductUpdateRequestDto.class);
        when(prdProductUpdateRequestDto.getName()).thenReturn("testProduct");
        when(prdProductUpdateRequestDto.getTaxFreePrice()).thenReturn(new BigDecimal(-80));
        when(prdProductUpdateRequestDto.getProductCategoryId()).thenReturn(4L);

        when(pctProductCategoryService.getVatRate(anyLong())).thenReturn(8);

        PrdProduct prdProduct = mock(PrdProduct.class);
        when(prdProductEntityService.getByIdWithControl(anyLong())).thenReturn(prdProduct);

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, ()
                -> prdProductService.updateProduct(prdProductUpdateRequestDto));

        assertEquals(PrdErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO, genBusinessException.getBaseErrorMessage());

        verify(pctProductCategoryService).getVatRate(anyLong());
    }

    @Test
    void shouldUpdateProductPrice() {

        PrdProduct prdProduct = mock(PrdProduct.class);
        BigDecimal price = new BigDecimal("50.99");
        when(prdProduct.getTaxFreePrice()).thenReturn(price);

        when(prdProductEntityService.getByIdWithControl(anyLong())).thenReturn(prdProduct);
        when(prdProductEntityService.save(any())).thenReturn(prdProduct);

        when(pctProductCategoryService.getVatRate(anyLong())).thenReturn(8);

        PrdProductResponseDto prdProductResponseDto = prdProductService.updateProductPrice(anyLong(), price);

        assertEquals(price, prdProductResponseDto.getTaxFreePrice());

        verify(prdProductEntityService).getByIdWithControl(anyLong());
        verify(prdProductEntityService).save(any());
    }

    @Test
    void shouldNotUpdateProductPriceWhenPriceIsNotValid() {

        BigDecimal price = new BigDecimal(-80);

        when(pctProductCategoryService.getVatRate(anyLong())).thenReturn(8);

        PrdProduct prdProduct = mock(PrdProduct.class);
        when(prdProductEntityService.getByIdWithControl(anyLong())).thenReturn(prdProduct);

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, ()
                -> prdProductService.updateProductPrice(anyLong(), price));

        assertEquals(PrdErrorMessage.PRICE_MUST_BE_GREATER_THAN_ZERO, genBusinessException.getBaseErrorMessage());

        verify(pctProductCategoryService).getVatRate(anyLong());
    }

    @Test
    void shouldBatchUpdateProductPrices() {
        Integer vatRate = 1;

        List<PrdProduct> prdProductList = new ArrayList<>();

        PrdProduct prdProduct = mock(PrdProduct.class);
        prdProductList.add(prdProduct);

        when(prdProduct.getTaxFreePrice()).thenReturn(new BigDecimal(150));
        when(prdProductEntityService.findAllByCategoryId(anyLong())).thenReturn(prdProductList);

        prdProductService.batchUpdateProductPrices(anyLong(), vatRate);
    }
}
