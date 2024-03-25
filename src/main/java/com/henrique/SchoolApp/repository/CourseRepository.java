package com.henrique.SchoolApp.repository;

import com.henrique.SchoolApp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {
}
