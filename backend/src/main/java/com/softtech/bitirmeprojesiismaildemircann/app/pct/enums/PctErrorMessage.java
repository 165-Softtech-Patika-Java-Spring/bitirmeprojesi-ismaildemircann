package com.softtech.bitirmeprojesiismaildemircann.app.pct.enums;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.BaseErrorMessage;

public enum PctErrorMessage implements BaseErrorMessage {

    PRODUCT_CATEGORY_DETAIL_INFO_NOT_FOUND("Product category detail information not found!"),
    PRODUCT_CATEGORY_UPDATE_VAT_RATE_ERROR("An error occurred while updating the vat rate"),
    ;

    private final String message;
    PctErrorMessage(String message) {
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
