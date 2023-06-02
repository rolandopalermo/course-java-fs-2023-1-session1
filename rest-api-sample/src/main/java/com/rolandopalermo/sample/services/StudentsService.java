package com.rolandopalermo.sample.services;

import com.rolandopalermo.sample.dtos.StudentCreationRequestDto;
import com.rolandopalermo.sample.dtos.StudentCreationResponseDto;
import com.rolandopalermo.sample.repositories.StudentsRepository;
import com.rolandopalermo.sample.repositories.entities.Student;
import com.rolandopalermo.sample.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentCreationResponseDto create(StudentCreationRequestDto request) {
        Student createdStudent = studentsRepository.save(toEntity(request));
        return toDto(createdStudent);
    }

    private Student toEntity(StudentCreationRequestDto request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBirthday(DateUtils.toLocalDate(request.getBirthday()));
        student.setInsertionDate(LocalDateTime.now());
        return student;
    }

    private StudentCreationResponseDto toDto(Student entity) {
        StudentCreationResponseDto student = new StudentCreationResponseDto();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setBirthday(DateUtils.toString(entity.getBirthday()));
        return student;
    }


}
