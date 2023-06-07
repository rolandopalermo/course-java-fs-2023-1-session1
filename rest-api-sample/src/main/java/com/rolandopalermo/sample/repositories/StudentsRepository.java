package com.rolandopalermo.sample.repositories;

import com.rolandopalermo.sample.repositories.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    @Query("SELECT student FROM Student student WHERE student.identificationNumber = :identificationNumber")
    Optional<Student> findByIdentificationNumber(String identificationNumber);

}
