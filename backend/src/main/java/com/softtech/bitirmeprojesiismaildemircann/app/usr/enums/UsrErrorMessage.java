package com.softtech.bitirmeprojesiismaildemircann.app.usr.enums;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.BaseErrorMessage;

public enum UsrErrorMessage implements BaseErrorMessage {

    USER_USERNAME_EXIST_MESSAGE("Username is already taken!"),
    ;

    private String message;
    UsrErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}
