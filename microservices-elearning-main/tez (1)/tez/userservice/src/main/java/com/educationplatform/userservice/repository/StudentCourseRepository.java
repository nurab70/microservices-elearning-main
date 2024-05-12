package com.educationplatform.userservice.repository;
import com.educationplatform.userservice.entity.Student;
import com.educationplatform.userservice.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StudentCourse,Long>{
    List<StudentCourse> findByStudentId(Long studentId);
    List<StudentCourse> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
