package com.example.restproject.endpoint;

import com.example.restproject.dto.SaveStudentRequest;
import com.example.restproject.dto.StudentResponseDto;
import com.example.restproject.exception.ResourceNotFoundException;
import com.example.restproject.model.Student;
import com.example.restproject.repository.StudentRepository;
import com.example.restproject.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Vagharhak Grigoryan
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentsEndpoint {
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final ModelMapper modelMapper;
    private final StudentRepository data;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/page-one")
    public List<Student> getPageOne() {

        Pageable paging = PageRequest.of(
                0, 2, Sort.by("id").ascending());
        Page<Student> page = data.findAll(paging);

        return page.getContent();
    }


    @GetMapping("/{studentID}")
    public ResponseEntity<Student> getStudentsById(@PathVariable(value = "studentID") Long studentId)
            throws ResourceNotFoundException {
        Student student = studentRepository.findById(studentId).orElseThrow(() ->
                new ResourceNotFoundException("Student not found on :: " + studentId));
        return ResponseEntity.ok().body(student);
    }

    @PostMapping
    public List<Student> add(@RequestBody Student student) {
        studentService.add(student);
        return studentService.list();
    }


    @DeleteMapping(path = "/{studentID}")
    public Map<String, Boolean> deleteStudent(@PathVariable(value = "studentID") Long studentID) throws Exception {
        studentService.deleteStudent(studentID);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping(path = "/{studentID}")
    public ResponseEntity<StudentResponseDto> updateStudent(@RequestBody SaveStudentRequest saveStudentRequest,
                                                            @PathVariable("studentID") int id) {
        Optional<Student> user = studentRepository.findById((long) id);
        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Student byId = user.get();
        if (saveStudentRequest.getEmail() != null) {
            byId.setEmail(saveStudentRequest.getEmail());
        }
        if (saveStudentRequest.getName() != null) {
            byId.setName(saveStudentRequest.getName());
        }
        if (saveStudentRequest.getDob() != null) {
            byId.setDob(saveStudentRequest.getDob());
        }

        studentRepository.save(byId);
        StudentResponseDto userResponseDto = modelMapper.map(byId, StudentResponseDto.class);
        return ResponseEntity.ok(userResponseDto);
    }
}