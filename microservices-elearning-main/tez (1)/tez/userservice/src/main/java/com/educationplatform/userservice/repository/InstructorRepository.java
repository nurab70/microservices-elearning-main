package com.educationplatform.userservice.repository;

import com.educationplatform.userservice.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
