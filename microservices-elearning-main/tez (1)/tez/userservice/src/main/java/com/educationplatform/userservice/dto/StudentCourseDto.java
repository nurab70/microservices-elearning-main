package com.educationplatform.userservice.dto;
import com.courserservice.courseservice.entity.Course;
import com.educationplatform.userservice.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseDto {
    private Long id;
    private Long studentId;
    private Long courseId;
}
