package com.softtech.bitirmeprojesiismaildemircann.app.usr.controller;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserSaveRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.request.UsrUserUpdateRequestDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.dto.response.UsrUserResponseDto;
import com.softtech.bitirmeprojesiismaildemircann.app.usr.service.UsrUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Operation(
            tags = "User", description = "This method registers a user.", summary = "Registers user",
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
    @PostMapping
    @Validated
    public ResponseEntity saveUser(@RequestBody @Valid UsrUserSaveRequestDto usrUserSaveRequestDto) {

        UsrUserResponseDto usrUserResponseDto = usrUserService.saveUser(usrUserSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserResponseDto));

    }

    @Operation(
            tags = "User", description = "This method updates a user's information.", summary = "Updates user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(
                                            implementation = UsrUserUpdateRequestDto.class
                                    ),
                                    examples = {
                                            @ExampleObject(
                                                    value = "{\"id\": \"1\",\"username\": \"ismail_demircan_2\",\"password\": \"87654321\",\"name\": \"ismail2\",\"surname\": \"demircan2\"}"
                                            )
                                    }
                            ),
                    }
            )
    )
    @PutMapping
    @Validated
    public ResponseEntity updateUser(@RequestBody @Valid UsrUserUpdateRequestDto usrUserUpdateRequestDto) {

        UsrUserResponseDto usrUserResponseDto = usrUserService.updateUser(usrUserUpdateRequestDto);

        return ResponseEntity.ok(RestResponse.of(usrUserResponseDto));
    }

    @Operation(tags = "User", description = "This method deletes a user whose id is given.", summary = "Delete user by given id")
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(
            @Parameter(name = "userId",in = ParameterIn.PATH, schema = @Schema(type = "number", example = "1"))
            @PathVariable Long userId) {

        usrUserService.deleteUser(userId);

        return ResponseEntity.ok(RestResponse.empty());

    }
}
