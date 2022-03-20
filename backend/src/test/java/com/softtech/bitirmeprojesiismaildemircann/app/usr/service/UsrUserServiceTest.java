package com.softtech.bitirmeprojesiismaildemircann.app.usr.service;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.ItemNotFoundException;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.enums.UsrErrorMessage;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.entityservice.UsrUserEntityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsrUserServiceTest {

    @Mock
    private UsrUserEntityService usrUserEntityService;

    @InjectMocks
    private UsrUserService usrUserService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldSaveUser() {
        UsrUserSaveRequestDto usrUserSaveRequestDto = mock(UsrUserSaveRequestDto.class);
        when(usrUserSaveRequestDto.getUsername()).thenReturn("ismail");
        when(usrUserSaveRequestDto.getPassword()).thenReturn("test123");

        when(usrUserEntityService.existsByUsername(anyString())).thenReturn(false);

        UsrUser usrUser = mock(UsrUser.class);
        when(usrUser.getId()).thenReturn(1L);
        when(passwordEncoder.encode(anyString())).thenReturn("enc_test");

        when(usrUserEntityService.save(any())).thenReturn(usrUser);

        UsrUserResponseDto usrUserResponseDto = usrUserService.saveUser(usrUserSaveRequestDto);

        assertEquals(1L, usrUserResponseDto.getId());

        verify(usrUserEntityService).existsByUsername(anyString());
    }

    @Test
    void shouldNotSaveUserWhenUsernameIsExist() {

        UsrUserSaveRequestDto usrUserSaveRequestDto = mock(UsrUserSaveRequestDto.class);
        when(usrUserSaveRequestDto.getUsername()).thenReturn("ismail");
        when(usrUserEntityService.existsByUsername(anyString())).thenReturn(true);

        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, ()
                -> usrUserService.saveUser(usrUserSaveRequestDto));

        assertEquals(UsrErrorMessage.USER_USERNAME_EXIST_MESSAGE, genBusinessException.getBaseErrorMessage());

        verify(usrUserEntityService).existsByUsername(anyString());
    }

    @Test
    void shouldUpdateUser() {
        UsrUserUpdateRequestDto usrUserUpdateRequestDto = mock(UsrUserUpdateRequestDto.class);
        when(usrUserUpdateRequestDto.getUsername()).thenReturn("ismail");
        when(usrUserUpdateRequestDto.getPassword()).thenReturn("test123");

        UsrUser usrUser = mock(UsrUser.class);
        Long id = 18L;
        String username = "ismail2";
        when(usrUser.getId()).thenReturn(id);
        when(usrUser.getUsername()).thenReturn(username);

        when(usrUserEntityService.getByIdWithControl(anyLong())).thenReturn(usrUser);
        when(usrUserEntityService.save(any())).thenReturn(usrUser);

        UsrUserResponseDto usrUserResponseDto = usrUserService.updateUser(usrUserUpdateRequestDto);

        assertEquals(id, usrUserResponseDto.getId());

        verify(usrUserEntityService).getByIdWithControl(anyLong());
        verify(usrUserEntityService).save(any());
    }

    @Test
    void shouldNotUpdateWhenUserDoesNotExist() {

        UsrUserUpdateRequestDto usrUserUpdateRequestDto = mock(UsrUserUpdateRequestDto.class);

        when(usrUserEntityService.getByIdWithControl(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> usrUserService.updateUser(usrUserUpdateRequestDto));

        verify(usrUserEntityService).getByIdWithControl(anyLong());
    }

    @Test
    void shouldNotUpdateUserWhenUsernameIsExist() {

        UsrUserUpdateRequestDto usrUserUpdateRequestDto = mock(UsrUserUpdateRequestDto.class);
        when(usrUserUpdateRequestDto.getUsername()).thenReturn("ismail2");
        when(usrUserUpdateRequestDto.getPassword()).thenReturn("test123");

        UsrUser usrUser = mock(UsrUser.class);
        when(usrUser.getUsername()).thenReturn("ismail");
        when(usrUserEntityService.getByIdWithControl(any())).thenReturn(usrUser);
        when(usrUserEntityService.existsByUsername(anyString())).thenReturn(true);


        GenBusinessException genBusinessException = assertThrows(GenBusinessException.class, ()
                -> usrUserService.updateUser(usrUserUpdateRequestDto));

        assertEquals(UsrErrorMessage.USER_USERNAME_EXIST_MESSAGE, genBusinessException.getBaseErrorMessage());

        verify(usrUserEntityService).getByIdWithControl(anyLong());
        verify(usrUserEntityService).existsByUsername(anyString());
    }

    @Test
    void shouldDeleteUser() {

        UsrUser usrUser = mock(UsrUser.class);

        when(usrUserEntityService.getByIdWithControl(anyLong())).thenReturn(usrUser);

        usrUserService.deleteUser(anyLong());

        verify(usrUserEntityService).getByIdWithControl(anyLong());
        verify(usrUserEntityService).delete(any());
    }

    @Test
    void shouldNotDeleteWhenIdDoesNotExist() {

        when(usrUserEntityService.getByIdWithControl(anyLong())).thenThrow(ItemNotFoundException.class);

        assertThrows(ItemNotFoundException.class, () -> usrUserService.deleteUser(anyLong()));

        verify(usrUserEntityService).getByIdWithControl(anyLong());
    }
}