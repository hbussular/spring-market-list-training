package com.henrique.SchoolApp.controller;
import com.henrique.SchoolApp.dto.CourseDto;
import com.henrique.SchoolApp.dto.CourseDto;
import com.henrique.SchoolApp.model.Course;
import com.henrique.SchoolApp.model.Course;
import com.henrique.SchoolApp.model.Course;
import com.henrique.SchoolApp.model.Course;
import com.henrique.SchoolApp.repository.CourseRepository;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;
    @GetMapping
    @Cacheable(value = "getCourses", sync = false)
    public List<CourseDto> getCourses() {
        List<Course> courses = courseRepository.findAll();
        return CourseDto.convert(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseDto> searchById(@PathVariable Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (course.isPresent()) {
            return ResponseEntity.ok(new CourseDto(course.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = "getCourses", allEntries = true)
    public Course add(@Valid @RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{courseId}")
    @CacheEvict(value = "getCourses", allEntries = true)
    public ResponseEntity<Course> update(@PathVariable Long courseId, @RequestBody Course course) {

        if(!courseRepository.existsById(courseId)) {
            return ResponseEntity.notFound().build();
        }

        course.setId(courseId);
        course = courseRepository.save(course);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{courseId}")
    @CacheEvict(value = "getCourses", allEntries = true)
    public ResponseEntity<Void> delete(@PathVariable Long courseId) {

        if(!courseRepository.existsById(courseId)) {
            return ResponseEntity.notFound().build();
        }

        courseRepository.deleteById(courseId);
        return ResponseEntity.noContent().build();
    }
}
