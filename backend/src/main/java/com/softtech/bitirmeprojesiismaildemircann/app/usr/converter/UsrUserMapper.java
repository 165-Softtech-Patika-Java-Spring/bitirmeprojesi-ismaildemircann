package com.softtech.bitirmeprojesiismaildemircann.app.usr.converter;

import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrUserMapper {

    UsrUserMapper INSTANCE = Mappers.getMapper(UsrUserMapper.class);

    UsrUser convertToUsrUser(UsrUserSaveRequestDto usrUserSaveRequestDto);

    UsrUser convertToUsrUser(UsrUserUpdateRequestDto usrUserUpdateRequestDto);

    UsrUserResponseDto convertToUsrUserResponseDto(UsrUser usrUser);

}
