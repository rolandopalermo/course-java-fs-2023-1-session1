package com.rolandopalermo.sample.controllers;

import com.rolandopalermo.sample.dtos.StudentCreationRequestDto;
import com.rolandopalermo.sample.dtos.StudentCreationResponseDto;
import com.rolandopalermo.sample.services.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1.0/students")
public class StudentsController {

    private final StudentsService studentsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCreationResponseDto create(@RequestBody StudentCreationRequestDto request) {
        return studentsService.create(request);
    }


}
