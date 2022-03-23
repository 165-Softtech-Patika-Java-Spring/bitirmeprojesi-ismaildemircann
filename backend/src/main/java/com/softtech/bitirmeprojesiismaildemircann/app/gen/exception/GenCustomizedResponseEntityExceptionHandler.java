package com.softtech.bitirmeprojesiismaildemircann.app.gen.exception;

import com.softtech.bitirmeprojesiismaildemircann.app.gen.dto.RestResponse;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.GenBusinessException;
import com.softtech.bitirmeprojesiismaildemircann.app.gen.exceptions.ItemNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
@Slf4j
public class GenCustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);

        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        String logErrorMessage = handleLogErrorMessage(message, description, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        log.error(logErrorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllItemNotFoundException(ItemNotFoundException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);

        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        String logErrorMessage = handleLogErrorMessage(message, description, String.valueOf(HttpStatus.NOT_FOUND.value()));
        log.error(logErrorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleAllGenBusinessException(GenBusinessException ex, WebRequest webRequest){

        Date errorDate = new Date();
        String message = ex.getBaseErrorMessage().getMessage();
        String description = webRequest.getDescription(false);

        GenExceptionResponse genExceptionResponse = new GenExceptionResponse(errorDate, message, description);

        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(genExceptionResponse);
        restResponse.setMessages(message);

        String logErrorMessage = handleLogErrorMessage(message, description, String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        log.error(logErrorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Date errorDate = new Date();
        String message = new StringBuilder(ex.getBindingResult().getFieldError().getField()).append(" field not valid").toString();
        String description = ex.getBindingResult().getFieldError().getDefaultMessage();

        GenExceptionResponse generalExceptionResponse = new GenExceptionResponse(errorDate,message,description);

        RestResponse<GenExceptionResponse> restResponse = RestResponse.error(generalExceptionResponse);
        restResponse.setMessages(message);

        String logErrorMessage = handleLogErrorMessage(message, description, String.valueOf(HttpStatus.BAD_REQUEST.value()));
        log.error(logErrorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    private String handleLogErrorMessage(String message, String detailMessage, String errorCode){

        String logErrorMessage = new StringBuilder("Message: ").append(message)
                .append(", Detail Message: ").append(detailMessage).append(", Error Code: ").append(errorCode).toString();

        return  logErrorMessage;
    }
}
