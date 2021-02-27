package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.APRIL;
import static java.time.Month.JANUARY;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
                Student mhamed = new Student(
                    "mhamed",
                    "mhamed@fayed.com",
                    LocalDate.of(1999, APRIL,5)

            );
                Student zeyad = new Student(
                        "zeyad",
                        "zeyad@faplab.com",
                        LocalDate.of(2000, JANUARY,8)

                );

                repository.saveAll(
                        List.of(mhamed,zeyad)
                );
        };
    }
}
