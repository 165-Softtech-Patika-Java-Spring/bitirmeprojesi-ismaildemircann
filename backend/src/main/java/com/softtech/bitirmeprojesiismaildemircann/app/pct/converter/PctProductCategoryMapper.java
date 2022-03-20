package com.softtech.bitirmeprojesiismaildemircann.app.pct.converter;

import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryDetailResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.dto.response.PctProductCategoryResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.PctProductCategory;
import com.softtech.bitirmeprojesiismaildemircann.app.pct.entity.result.PctProductCategoryDetailResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PctProductCategoryMapper {

    PctProductCategoryMapper INSTANCE = Mappers.getMapper(PctProductCategoryMapper.class);

    PctProductCategoryResponseDto convertToPctProductCategoryResponseDto(PctProductCategory pctProductCategory);

    List<PctProductCategoryResponseDto> convertToPctProductCategoryResponseDtoList(List<PctProductCategory> pctProductCategoryList);

    List<PctProductCategoryDetailResponseDto> convertToPctProductCategoryDetailResultList(List<PctProductCategoryDetailResult> pctProductCategoryDetailResultList);
}
