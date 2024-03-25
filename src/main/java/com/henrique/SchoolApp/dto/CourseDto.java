package com.henrique.SchoolApp.dto;

import com.henrique.SchoolApp.model.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {
    private Long id;
    private String courseName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long grade;

    public CourseDto(Course course) {
        this.id = course.getId();
        this.courseName = course.getCourseName();
        this.startDate = course.getStartDate();
        this.endDate = course.getEndDate();
        this.grade = course.getGrade();
    }

    public static List<CourseDto> convert(List<Course> course) {
        return course.stream().map(CourseDto::new).collect(Collectors.toList());
    }
}
