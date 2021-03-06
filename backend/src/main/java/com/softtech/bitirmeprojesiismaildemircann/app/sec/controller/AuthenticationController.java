package com.softtech.bitirmeprojesiismaildemircann.app.sec.controller;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.sec.dto.SecLoginRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.sec.service.AuthenticationService;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(
            tags = "Authentication", description = "This method return token using by given user information.", summary = "Login user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = SecLoginRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"username\": \"ismail_demircan\",\"password\": \"12345678\"}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @Validated
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid SecLoginRequestDto secLoginRequestDto) {

        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(
            tags = "Authentication", description = "This method registers a user.", summary = "Registers user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsrUserSaveRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"username\": \"ismail_demircan\",\"password\": \"12345678\",\"name\": \"ismail\",\"surname\": \"demircan\"}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @Validated
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsrUserSaveRequestDto usrUserSaveRequestDto) {

        UsrUserResponseDto usrUserResponseDto = authenticationService.register(usrUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserResponseDto));
    }
}
