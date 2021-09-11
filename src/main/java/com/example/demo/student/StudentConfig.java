package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repo){
        return args -> {

            Student houssem = new Student(
                    "houssem",
                    "houssem@gmail.com",
                    LocalDate.of(1999, Month.FEBRUARY,13)
            );

            Student cristiano = new Student(
                    "cristiano",
                    "cris@gmail.com",
                    LocalDate.of(1986, Month.FEBRUARY,13)
            );

            repo.saveAll(List.of(houssem,cristiano));

        };

    }


}
