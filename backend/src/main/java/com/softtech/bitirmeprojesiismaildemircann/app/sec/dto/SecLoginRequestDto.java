package com.softtech.bitirmeprojesiismaildemircann.app.sec.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SecLoginRequestDto {

    @NotEmpty(message = "Username cannot be null or blank!")
    private String username;

    @NotEmpty(message = "Password cannot be null or blank!")
    private String password;
}
