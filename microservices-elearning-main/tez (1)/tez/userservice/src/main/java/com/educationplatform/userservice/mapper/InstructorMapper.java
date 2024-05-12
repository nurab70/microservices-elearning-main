package com.educationplatform.userservice.mapper;
import com.educationplatform.userservice.dto.InstructorDto;
import com.educationplatform.userservice.entity.Instructor;

public class InstructorMapper {

    public static InstructorDto mapToInstructorDto(Instructor instructor) {
        return new InstructorDto(
                instructor.getId(),
                instructor.getName(),
                instructor.getSurname(),
                instructor.getEmail(),
                instructor.getPassword(),
                instructor.getPhone(),
                instructor.getAvatar()

        );
    }

    public static Instructor mapToInstructor(InstructorDto instructorDto) {
        return new Instructor(
                instructorDto.getId(),
                instructorDto.getName(),
                instructorDto.getSurname(),
                instructorDto.getEmail(),
                instructorDto.getPassword(),
                instructorDto.getPhone(),
                instructorDto.getAvatar()
        );
    }
}
