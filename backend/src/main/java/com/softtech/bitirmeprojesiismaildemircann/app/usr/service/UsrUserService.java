package com.softtech.bitirmeprojesiismaildemircann.app.usr.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.converter.UsrUserMapper;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.enums.UsrErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.entityservice.UsrUserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsrUserService {

    private final UsrUserEntityService usrUserEntityService;
    private final PasswordEncoder passwordEncoder;

    public UsrUserResponseDto saveUser(UsrUserSaveRequestDto usrUserSaveRequestDto) {

        String username = usrUserSaveRequestDto.getUsername();
        controlUsernameIsExist(username);

        UsrUser usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserSaveRequestDto);

        String encrPassword = passwordEncoder.encode(usrUserSaveRequestDto.getPassword());
        usrUser.setPassword(encrPassword);

        usrUser = usrUserEntityService.save(usrUser);

        UsrUserResponseDto usrUserResponseDto = UsrUserMapper.INSTANCE.convertToUsrUserResponseDto(usrUser);;

        return usrUserResponseDto;
    }

    public UsrUserResponseDto updateUser(UsrUserUpdateRequestDto usrUserUpdateRequestDto) {

        Long userId = usrUserUpdateRequestDto.getId();
        UsrUser usrUser = usrUserEntityService.getByIdWithControl(userId);

        String oldUsername = usrUser.getUsername();
        String newUsername = usrUserUpdateRequestDto.getUsername();

        usrUser = UsrUserMapper.INSTANCE.convertToUsrUser(usrUserUpdateRequestDto);

        setUsername(usrUser, oldUsername, newUsername);

        String encrPassword = passwordEncoder.encode(usrUserUpdateRequestDto.getPassword());
        usrUser.setPassword(encrPassword);

        usrUser = usrUserEntityService.save(usrUser);

        UsrUserResponseDto usrUserResponseDto = UsrUserMapper.INSTANCE.convertToUsrUserResponseDto(usrUser);;

        return usrUserResponseDto;
    }

    public void deleteUser(Long userId) {
        UsrUser usrUser = usrUserEntityService.getByIdWithControl(userId);
        usrUserEntityService.delete(usrUser);
    }

    private void controlUsernameIsExist(String username) {
        if(usrUserEntityService.existsByUsername(username)) {
            throw new GenBusinessException(UsrErrorMessage.USER_USERNAME_EXIST_MESSAGE);
        }
    }

    private void setUsername(UsrUser usrUser, String oldUsername, String newUsername) {
        if(!controlUsernameIsEqual(oldUsername, newUsername)) {
            controlUsernameIsExist(newUsername);
            usrUser.setUsername(newUsername);
        }
    }

    private Boolean controlUsernameIsEqual(String oldUsername, String newUsername) {
        return oldUsername.equals(newUsername);
    }
}
