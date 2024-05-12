package com.educationplatform.userservice.entity;



import com.courserservice.courseservice.entity.Course;
import jakarta.persistence.*;
//import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student_course")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "studentId")
    private Long studentId;

    @Column(name = "courseId")
    private Long courseId;
}
