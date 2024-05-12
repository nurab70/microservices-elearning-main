package com.educationplatform.userservice.repository;

import com.educationplatform.userservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student,Long> {
}
