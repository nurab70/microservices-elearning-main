package com.educationplatform.userservice.mapper;
import com.educationplatform.userservice.dto.StudentDto;
import com.educationplatform.userservice.entity.Student;

public class StudentMapper {

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getEmail(),
                student.getPassword(),
                student.getPhone(),
                student.getAvatar()

        );
    }

    public static Student mapToStudent(StudentDto studentDto) {
        return new Student(
                studentDto.getId(),
                studentDto.getName(),
                studentDto.getSurname(),
                studentDto.getEmail(),
                studentDto.getPassword(),
                studentDto.getPhone(),
                studentDto.getAvatar()
        );
    }
}
