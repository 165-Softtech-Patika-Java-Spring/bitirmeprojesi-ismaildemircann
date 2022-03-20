package com.softtech.bitirmeprojesiismaildemircann.app.usr.service.entityservice;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.service.BaseEntityService;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dao.UsrUserDao;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.entity.UsrUser;
import org.springframework.stereotype.Service;

@Service
public class UsrUserEntityService extends BaseEntityService<UsrUser, UsrUserDao> {

    public UsrUserEntityService(UsrUserDao dao) {
        super(dao);
    }

    public Boolean existsByUsername(String username) {
        return getDao().existsByUsername(username);
    }

    public UsrUser findByUsername(String username) {
        return getDao().findByUsername(username);
    }
}
