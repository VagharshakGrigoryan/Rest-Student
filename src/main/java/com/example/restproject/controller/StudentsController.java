package com.example.restproject.controller;

import com.example.restproject.model.Student;
import com.example.restproject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Vagharhak Grigoryan
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/students")
public class StudentsController {

    private final StudentService studentService;

    @GetMapping(path = "list")
    public List<Student> list() {
        return studentService.list();
    }

    @PostMapping(path = "item")
    public List<Student> add(@RequestBody Student student) {

        studentService.add(student);
        return studentService.list();
    }

    @DeleteMapping (path = "item/{studentID}")
    public void delete (@PathVariable Long studentID) {

        studentService.delete(studentID);
    }

    @PutMapping (path = "item")
    public void update (@RequestBody Student student ) {

        studentService.update(student);
    }
}
