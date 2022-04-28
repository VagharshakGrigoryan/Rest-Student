package com.example.restproject.repository;

import com.example.restproject.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Vagharhak Grigoryan
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<String> findStudentByEmail(String email);

}
