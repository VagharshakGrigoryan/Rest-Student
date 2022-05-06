package com.example.restproject.service;

import com.example.restproject.exception.ResourceNotFoundException;
import com.example.restproject.model.Student;
import com.example.restproject.repository.StudentRepository;
import com.example.restproject.response.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vagharhak Grigoryan
 */

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    @GetMapping(path = "students")
    public List<Student> list() {
        return studentRepository.findAll();
    }

    public void add(Student student) {

        if (studentRepository.findStudentByEmail(student.getEmail()).isPresent()) {
            throw new RestApiException("Email is busy");
        }
        studentRepository.save(student);
    }


    public void deleteStudent(@PathVariable(value = "studentID") Long studentID) throws Exception {
        Student student = studentRepository.findById(studentID).orElseThrow(() ->
                new ResourceNotFoundException("Student not found on :: " + studentID));

        studentRepository.delete(student);
        @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
    }
}