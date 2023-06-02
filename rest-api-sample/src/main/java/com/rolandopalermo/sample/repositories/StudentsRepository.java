package com.rolandopalermo.sample.repositories;

import com.rolandopalermo.sample.repositories.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsRepository extends JpaRepository<Student, Long> {
}
