package com.educationplatform.userservice.service;
import com.educationplatform.userservice.dto.StudentCourseDto;

import java.util.List;
public interface StudentCourseService {
    StudentCourseDto createStudentCourse(StudentCourseDto studentCourseDto);

    //student idsinden aldığı tüm kursları listeliyor
    List<StudentCourseDto> getStudentCourseByStudentId(Long studentId);
    StudentCourseDto getStudentCourseById(Long studentCourseId);

    List<StudentCourseDto> getAllStudentCourses();

    StudentCourseDto updateStudentCourse(Long studentCourseId, StudentCourseDto updateStudentCourse);

    void deleteStudentCourse(Long studentCourseId);
}
