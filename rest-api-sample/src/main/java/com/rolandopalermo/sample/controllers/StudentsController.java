package com.rolandopalermo.sample.controllers;

import com.rolandopalermo.sample.dtos.StudentCreationRequestDto;
import com.rolandopalermo.sample.dtos.StudentCreationResponseDto;
import com.rolandopalermo.sample.services.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1.0/students")
public class StudentsController {

    private final StudentsService studentsService;

    /**
     * Safe = No side effects on the overall state of the server, it does not modify the state of the server
     * Idempotent = Executing identical requests multiple times will have the same effect as executing only one request
     */

    /**
     * Safe = YES
     * Idempotent = YES
     */
    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentCreationResponseDto findById(@PathVariable("id") long id) {
        return studentsService.findById(id);
    }


    /**
     * Safe = YES
     * Idempotent = YES
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StudentCreationResponseDto> findAll(Pageable pageable) {
        return studentsService.findAll(pageable);
    }

    /**
     * Safe = NO
     * Idempotent = NO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentCreationResponseDto create(@RequestBody StudentCreationRequestDto request) {
        return studentsService.create(request);
    }

    /**
     * Safe = NO
     * Idempotent = YES
     */
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentCreationResponseDto update(
            @PathVariable("id") long id,
            @RequestBody StudentCreationRequestDto request) {
        return studentsService.update(id, request);
    }

    /**
     * Safe = NO
     * Idempotent = YES
     */
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        studentsService.delete(id);
    }

}
