package com.rolandopalermo.sample.dtos;

import com.rolandopalermo.sample.utils.DateUtils;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class ErrorDto {

    private String timestamp;
    private String resource;
    private List<String> messages;

    public String getTimestamp() {
        return DateUtils.toString(LocalDate.now());
    }

}
