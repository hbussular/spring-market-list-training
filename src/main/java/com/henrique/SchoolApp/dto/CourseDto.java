package com.henrique.SchoolApp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.henrique.SchoolApp.model.Course;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    private Long id;
    private String CourseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long grade;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.CourseName = course.getCourseName();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        this.grade = course.getGrade();
    }
}
