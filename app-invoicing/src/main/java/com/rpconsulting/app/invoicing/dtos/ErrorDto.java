package com.rpconsulting.app.invoicing.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorDto {

    private String message;

    private LocalDateTime localDateTime;

    private String url;


    private  String method;

}
