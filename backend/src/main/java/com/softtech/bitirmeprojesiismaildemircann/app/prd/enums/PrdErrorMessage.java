package com.softtech.bitirmeprojesiismaildemircann.app.prd.enums;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.enums.BaseErrorMessage;

public enum PrdErrorMessage implements BaseErrorMessage {

    PRODUCT_NOT_FOUND("Product not found!"),
    PRODUCT_NOT_FOUND_FOR_THIS_SEARCH_CRITERIA("Product not found for this search criteria!"),
    PRICE_MUST_BE_GREATER_THAN_ZERO("Product price cannot be zero or negative.")
    ;

    private final String message;
    PrdErrorMessage(String message) {
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
