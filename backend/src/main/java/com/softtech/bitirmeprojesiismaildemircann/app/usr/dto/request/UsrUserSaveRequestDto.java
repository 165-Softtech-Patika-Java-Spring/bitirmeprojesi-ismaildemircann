package com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class UsrUserSaveRequestDto {

    @NotEmpty(message = "Username cannot be null or blank!")
    private String username;

    @NotEmpty(message = "Password cannot be null or blank!")
    private String password;

    private String name;

    private String surname;
}
