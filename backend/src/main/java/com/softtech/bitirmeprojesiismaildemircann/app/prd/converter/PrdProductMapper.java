package com.softtech.bitirmeprojesiismaildemircann.app.prd.converter;

import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.request.PrdProductSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.dto.response.PrdProductResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.prd.entity.PrdProduct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrdProductMapper {

    PrdProductMapper INSTANCE = Mappers.getMapper(PrdProductMapper.class);

    PrdProduct convertToPrdProduct(PrdProductSaveRequestDto prdProductSaveRequestDto);

    PrdProductResponseDto convertToPrdProductResponseDto(PrdProduct prdProduct);

    List<PrdProductResponseDto> convertToPrdProductResponseDtoList(List<PrdProduct> prdProductList);
}
