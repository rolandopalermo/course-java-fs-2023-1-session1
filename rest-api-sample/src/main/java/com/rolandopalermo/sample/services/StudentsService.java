package com.rolandopalermo.sample.services;

import com.rolandopalermo.sample.dtos.NestedErrorDto;
import com.rolandopalermo.sample.dtos.StudentCreationRequestDto;
import com.rolandopalermo.sample.dtos.StudentCreationResponseDto;
import com.rolandopalermo.sample.exceptions.AlreadyExistsV2Exception;
import com.rolandopalermo.sample.repositories.StudentsRepository;
import com.rolandopalermo.sample.repositories.entities.Student;
import com.rolandopalermo.sample.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentCreationResponseDto create(StudentCreationRequestDto request) {
        Optional<Student> student = studentsRepository.findFirstByDni(request.getDni());

        List<NestedErrorDto> nestedErrors = new ArrayList<>();
        nestedErrors.add(new NestedErrorDto("Nested error 1",Arrays.asList("Sub Error 1", "Sub Error 2")));
        nestedErrors.add(new NestedErrorDto("Nested error 2",Arrays.asList("Sub Error 1", "Sub Error 2")));
        nestedErrors.add(new NestedErrorDto("Nested error 3",Arrays.asList("Sub Error 1", "Sub Error 2")));


        if(student.isPresent()) {
//            throw new AlreadyExistsV2Exception(Arrays.asList("El estudiante ya existe", "error 2"));
            throw new AlreadyExistsV2Exception(nestedErrors);
        }
        Student createdStudent = studentsRepository.save(toEntity(request));
        return toDto(createdStudent);
    }


    private Student toEntity(StudentCreationRequestDto request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBirthday(DateUtils.toLocalDate(request.getBirthday()));
        student.setInsertionDate(LocalDateTime.now());
        student.setDni(request.getDni());
        return student;
    }

    private StudentCreationResponseDto toDto(Student entity) {
        StudentCreationResponseDto student = new StudentCreationResponseDto();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setBirthday(DateUtils.toString(entity.getBirthday()));
        student.setDni(entity.getDni());
        return student;
    }


}
