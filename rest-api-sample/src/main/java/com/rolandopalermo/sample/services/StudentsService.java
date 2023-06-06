package com.rolandopalermo.sample.services;

import com.rolandopalermo.sample.dtos.StudentCreationRequestDto;
import com.rolandopalermo.sample.dtos.StudentCreationResponseDto;
import com.rolandopalermo.sample.exceptions.AlreadyExistsException;
import com.rolandopalermo.sample.repositories.StudentsRepository;
import com.rolandopalermo.sample.repositories.entities.Student;
import com.rolandopalermo.sample.utils.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentCreationResponseDto create(StudentCreationRequestDto request) {
        Optional<Student> existingStudent = studentsRepository.findByIdentificationNumber(request.getIdNumber());

        if (existingStudent.isPresent()) {
            throw new AlreadyExistsException(format("A student with id number {0} already exists", request.getIdNumber()));
        }

        Student createdStudent = studentsRepository.save(toEntity(request));
        return toDto(createdStudent);
    }

    public StudentCreationResponseDto findById(long id) {
        Student student = findFirstById(id);
        return toDto(student);
    }

    public StudentCreationResponseDto update(long id, StudentCreationRequestDto request) {
        Student student = findFirstById(id);
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBirthday(DateUtils.toLocalDate(request.getBirthday()));

        Student updatedStudent = studentsRepository.save(student);
        return toDto(updatedStudent);
    }

    public Page<StudentCreationResponseDto> findAll(Pageable pageable) {
        Page<Student> studentsPage = studentsRepository.findAll(pageable);

        return new PageImpl<>(
                studentsPage.getContent()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList()),
                studentsPage.getPageable(),
                studentsPage.getTotalElements());
    }

    public void delete(long id) {
        studentsRepository.findById(id).ifPresent(studentsRepository::delete);
    }

    private Student findFirstById(long id) {
        return studentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(format("Unable to find a student with id {0}", id)));
    }

    private Student toEntity(StudentCreationRequestDto request) {
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setBirthday(DateUtils.toLocalDate(request.getBirthday()));
        student.setInsertionDate(LocalDateTime.now());
        student.setIdentificationNumber(request.getIdNumber());
        return student;
    }

    private StudentCreationResponseDto toDto(Student entity) {
        StudentCreationResponseDto student = new StudentCreationResponseDto();
        student.setId(entity.getId());
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setBirthday(DateUtils.toString(entity.getBirthday()));
        student.setIdNumber(entity.getIdentificationNumber());
        return student;
    }

}
