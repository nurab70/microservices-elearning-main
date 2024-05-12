package com.courserservice.courseservice.mapper;

import com.courserservice.courseservice.dto.CourseDto;
import com.courserservice.courseservice.entity.Course;

public class CourseMapper {
    public  static CourseDto mapToCourseDto(Course course){
        return new CourseDto(
                course.getId(),
                course.getCourseName(),
                course.getCourseType(),
                course.getDescription(),
                course.getType(),
                course.getAttachmentPath(),
                course.getInstructorId()
        );
    }
    public static Course mapToUser(CourseDto courseDto){
        return new Course(
                courseDto.getId(),
                courseDto.getCourseName(),
                courseDto.getCourseType(),
                courseDto.getDescription(),
                courseDto.getType(),
                courseDto.getAttachmentPath(),
                courseDto.getInstructorId()
        );
    }
}
