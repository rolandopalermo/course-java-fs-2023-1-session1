package com.rolandopalermo.sample.repositories;

import com.rolandopalermo.sample.repositories.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {

    // JPQL
    @Query("SELECT s FROM Student s WHERE s.dni = :dni")
    Optional<Student> findFirstByDni(String dni);
}
