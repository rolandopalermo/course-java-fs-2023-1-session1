package com.rpconsulting.app.invoicing.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDto {
	private String messages;
	private LocalDateTime timestamp;
	private String url;
}
