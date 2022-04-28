package com.example.restproject.service;

import com.example.restproject.model.Student;
import com.example.restproject.repository.StudentRepository;
import com.example.restproject.response.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

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

        if(studentRepository.findStudentByEmail(student.getEmail()).isPresent()){
            throw new RestApiException("Email is busy");
        }
        studentRepository.save(student);
    }


    public void delete(Long studentID) {
        studentRepository.deleteById(studentID);
    }

    public void update(Student student) {
        Optional<Student> row = studentRepository.findById(student.getId());
        if (row.isPresent()) {
            Student item = row.get();
            if (!student.getName().isEmpty()) {
                item.setName(student.getName());
            }
            if (student.getDob() != null) {
                item.setDob(student.getDob());
            }
            studentRepository.save(item);

        }
    }
}
