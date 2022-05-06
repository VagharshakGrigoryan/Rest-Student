package com.example.restproject.dto;

import com.example.restproject.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
    private Student student;
}
