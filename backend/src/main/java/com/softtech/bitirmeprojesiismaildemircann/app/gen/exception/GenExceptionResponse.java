package com.softtech.bitirmeprojesiismaildemircann.app.gen.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GenExceptionResponse {

    private Date errorDate;
    private String message;
    private String detail;
}
