package com.example.restproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveStudentRequest {

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;

}