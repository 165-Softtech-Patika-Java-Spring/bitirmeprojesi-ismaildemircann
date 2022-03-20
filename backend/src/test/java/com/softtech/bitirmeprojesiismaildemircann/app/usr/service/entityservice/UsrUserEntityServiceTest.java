package com.softtech.bitirmeprojesiismaildemircann.app.usr.service.entityservice;

import com.softtech.bitirmeprojesiismaildemircann.app.usr.dao.UsrUserDao;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.UsrUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsrUserEntityServiceTest {

    @Mock
    private UsrUserDao usrUserDao;

    @InjectMocks
    private UsrUserEntityService usrUserEntityService;

    @Test
    void ShouldExistsByUsername() {

        when(usrUserDao.existsByUsername(anyString())).thenReturn(true);

        Boolean result = usrUserEntityService.existsByUsername(anyString());

        assertEquals(true, result);
    }

    @Test
    void ShouldFindByUsername() {

        UsrUser usrUser = mock(UsrUser.class);
        when(usrUserDao.findByUsername(anyString())).thenReturn(usrUser);
        when(usrUser.getId()).thenReturn(1L);

        UsrUser result = usrUserEntityService.findByUsername(anyString());

        assertEquals(1L, result.getId());
    }
}