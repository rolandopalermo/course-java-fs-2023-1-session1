package com.rolandopalermo.sample.dtos;

import lombok.Data;

@Data
public class StudentCreationResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthday;

}
