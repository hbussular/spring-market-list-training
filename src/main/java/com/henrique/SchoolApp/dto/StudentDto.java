package com.henrique.SchoolApp.dto;

import com.henrique.SchoolApp.model.Course;
import com.henrique.SchoolApp.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentDto {

    private Long id;

    private String username;


    private String email;
    private String name;
    private String surname;
    private Integer age;

    private String cpf;
    private Course courses;

    public StudentDto(Student student) {
        this.id = student.getId();
        this.username = student.getUsername();
        this.email = student.getEmail();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.age = student.getAge();
        this.cpf = student.getCpf();
        this.courses = student.getCourse();
    }

    public static List<StudentDto> convert(List<Student> student) {
        return student.stream().map(StudentDto::new).collect(Collectors.toList());
    }
}
