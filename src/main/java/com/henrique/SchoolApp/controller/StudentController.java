package com.henrique.SchoolApp.controller;

import com.henrique.SchoolApp.dto.StudentDto;
import com.henrique.SchoolApp.model.Student;
import com.henrique.SchoolApp.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping
    public List<StudentDto> getStudents() {
        List<Student> students = studentRepository.findAll();
        return StudentDto.convert(students);
    }

    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student id not found."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student add(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> update(@PathVariable Long studentId, @RequestBody Student student) {

        if(!studentRepository.existsById(studentId)) {
            return ResponseEntity.notFound().build();
        }

        student.setId(studentId);
        student = studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{studentId}")
        public ResponseEntity<Void> delete(@PathVariable Long studentId) {

            if(!studentRepository.existsById(studentId)) {
                return ResponseEntity.notFound().build();
            }

            studentRepository.deleteById(studentId);
            return ResponseEntity.noContent().build();
    }
}
