package com.softtech.bitirmeprojesiismaildemircann.app.usr.controller;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.UsrUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsrUserController {

    private final UsrUserService usrUserService;

    @PostMapping
    @Validated
    public ResponseEntity saveUser(@RequestBody @Valid UsrUserSaveRequestDto usrUserSaveRequestDto) {

        UsrUserResponseDto usrUserResponseDto = usrUserService.saveUser(usrUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserResponseDto));

    }

    @PutMapping
    @Validated
    public ResponseEntity updateUser(@RequestBody @Valid UsrUserUpdateRequestDto usrUserUpdateRequestDto) {

        UsrUserResponseDto usrUserResponseDto = usrUserService.updateUser(usrUserUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserResponseDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable Long userId) {

        usrUserService.deleteUser(userId);

        return ResponseEntity.ok(RestResponse.empty());

    }
}
