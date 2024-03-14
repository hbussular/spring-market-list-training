package com.henrique.SchoolApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column @NotEmpty @NotNull
    private String courseName;
    @Column @NotEmpty @NotNull
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private Long grade;

    public Course(Course course) {
        this.id = id;
        this.courseName = courseName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.grade = grade;
    }
}
