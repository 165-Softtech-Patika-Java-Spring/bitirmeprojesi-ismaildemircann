package com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.BaseErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNotFoundException extends GenBusinessException{

    public ItemNotFoundException(BaseErrorMessage message) {
        super(message);
    }
}
