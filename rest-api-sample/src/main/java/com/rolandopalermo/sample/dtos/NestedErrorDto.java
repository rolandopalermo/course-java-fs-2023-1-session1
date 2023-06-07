package com.rolandopalermo.sample.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NestedErrorDto {
    private String name;
    private List<String> subErrors;

    public NestedErrorDto(String name, List<String> subErrors) {
        this.name = name;
        this.subErrors = subErrors;
    }
}
