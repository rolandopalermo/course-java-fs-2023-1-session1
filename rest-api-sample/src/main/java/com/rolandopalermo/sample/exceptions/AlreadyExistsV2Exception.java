package com.rolandopalermo.sample.exceptions;

import com.rolandopalermo.sample.dtos.NestedErrorDto;
import lombok.Getter;

import java.util.List;

@Getter
public class AlreadyExistsV2Exception extends RuntimeException {
    private List<String> errors;
    private List<NestedErrorDto> nestedErrors;
//    public AlreadyExistsV2Exception(List<String> errors) {
//        this.errors = errors;
//    }

    public AlreadyExistsV2Exception(List<NestedErrorDto> nestedErrors) {
        this.nestedErrors = nestedErrors;
    }

}
