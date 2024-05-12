package com.educationplatform.userservice.service;

import com.educationplatform.userservice.dto.InstructorDto;

import java.util.List;

public interface InstructorService {
    InstructorDto createInstructor(InstructorDto instructorDto);

    InstructorDto getInstructorById(Long instructorId);
    List<InstructorDto> getAllInstructors();

    InstructorDto updateInstructor(Long instructorId, InstructorDto updateInstructor);

    void deleteInstructor(Long instructorId);


}
