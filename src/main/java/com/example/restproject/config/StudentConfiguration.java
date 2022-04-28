package com.example.restproject.config;

import com.example.restproject.model.Student;
import com.example.restproject.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {

    @Bean
    public CommandLineRunner commandLineRunner (StudentRepository studentRepository){
        return args -> {
            studentRepository.saveAll(List.of(
                    new Student(1L,"Alex", "user@mail.ru",LocalDate.of(2000, Month.APRIL,30)),
                    new Student(2L,"Adam","admin@mail.ru", LocalDate.of(1990, Month.OCTOBER,9))
            ));
        };
    }
}
