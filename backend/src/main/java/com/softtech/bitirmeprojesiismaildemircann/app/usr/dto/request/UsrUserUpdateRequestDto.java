package com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class UsrUserUpdateRequestDto {

    @NotNull(message = "Id cannot be null!")
    private Long id;

    @NotEmpty(message = "Username cannot be null or blank!")
    private String username;

    @NotEmpty(message = "Password cannot be null or blank!")
    private String password;

    private String name;

    private String surname;
}
