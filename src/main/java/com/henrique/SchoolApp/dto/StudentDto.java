package com.henrique.SchoolApp.dto;

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

    public StudentDto(Student student) {
        this.id = student.getId();
        this.username = student.getUsername();
        this.email = student.getEmail();
        this.name = student.getName();
        this.surname = student.getSurname();
        this.age = student.getAge();
    }

    public static List<StudentDto> convert(List<Student> student) {
        return student.stream().map(StudentDto::new).collect(Collectors.toList());
    }
}
