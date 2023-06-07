package com.rolandopalermo.sample.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorDto {
    private List<String> errors;
    private List<NestedErrorDto> nestedErrors;
    private LocalDateTime timestamp;
    private String url;
}
