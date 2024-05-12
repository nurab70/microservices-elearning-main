package com.educationplatform.userservice.mapper;
import com.educationplatform.userservice.dto.StudentCourseDto;
import com.educationplatform.userservice.entity.StudentCourse;
public class StudentCourseMapper {

    public static StudentCourseDto mapToStudentCourseDto(StudentCourse studentCourse) {

        return new StudentCourseDto(
                studentCourse.getId(),
                studentCourse.getStudentId(),
                studentCourse.getCourseId()
        );
    }

    public static StudentCourse mapToStudentCourse(StudentCourseDto studentCourseDto) {

        return new StudentCourse(
                studentCourseDto.getId(),
                studentCourseDto.getStudentId(),
                studentCourseDto.getCourseId()
        );
    }
}
