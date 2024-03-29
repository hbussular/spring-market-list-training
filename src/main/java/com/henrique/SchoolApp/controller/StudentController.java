package com.henrique.SchoolApp.controller;

import com.henrique.SchoolApp.dto.StudentDto;
import com.henrique.SchoolApp.model.Student;
import com.henrique.SchoolApp.repository.StudentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping
    @Cacheable(value = "getStudents", sync = false)
    public Page<StudentDto> getStudents(@RequestParam(required = false) String studentId, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pagination ) {
        Page<Student> students = studentRepository.findAll(pagination);
        return StudentDto.convert(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> searchById(@PathVariable Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return ResponseEntity.ok(new StudentDto(student.get()));
        }
        return ResponseEntity.notFound().build();
    }


//    @GetMapping("/{studentId}")
//    public Student getStudentById(@PathVariable Long studentId) {
//        return studentRepository.findById(studentId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Student id not found."));
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "getStudents", allEntries = true)
    public Student add(@Valid @RequestBody Student student) {
        return studentRepository.save(student);
    }

    @PutMapping("/{studentId}")
    @CacheEvict(value = "getStudents", allEntries = true)
    public ResponseEntity<Student> update(@PathVariable Long studentId, @RequestBody Student student) {

        if(!studentRepository.existsById(studentId)) {
            return ResponseEntity.notFound().build();
        }

        student.setId(studentId);
        student = studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{studentId}")
    @CacheEvict(value = "getStudents", allEntries = true)
        public ResponseEntity<Void> delete(@PathVariable Long studentId) {

            if(!studentRepository.existsById(studentId)) {
                return ResponseEntity.notFound().build();
            }

            studentRepository.deleteById(studentId);
            return ResponseEntity.noContent().build();
    }
}
