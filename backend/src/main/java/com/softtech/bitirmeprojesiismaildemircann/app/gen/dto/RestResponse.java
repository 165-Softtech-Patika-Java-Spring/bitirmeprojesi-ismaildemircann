package com.softtech.bitirmeprojesiismaildemircann.app.gen.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> implements Serializable {

    private T data;
    private Date responseDate;
    private boolean isSuccess;
    private String messages;

    public RestResponse(T data, boolean isSuccess) {
        this.data = data;
        this.isSuccess = isSuccess;
        responseDate = new Date();
    }

    public static <T> RestResponse<T> of(T t){
        return new RestResponse<>(t, true);
    }

    public static <T> RestResponse<T> error(T t){
        return new RestResponse<>(t, false);
    }

    public static <T> RestResponse<T> empty(){
        return new RestResponse<>(null, true);
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }
}
