package com.henrique.SchoolApp.repository;

import com.henrique.SchoolApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
