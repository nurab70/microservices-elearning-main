package com.educationplatform.userservice.service.impl;

import com.educationplatform.userservice.dto.InstructorDto;
import com.educationplatform.userservice.entity.Instructor;
import com.educationplatform.userservice.exception.ResourceNotFoundException;
import com.educationplatform.userservice.mapper.InstructorMapper;
import com.educationplatform.userservice.repository.InstructorRepository;
import com.educationplatform.userservice.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private InstructorRepository instructorRepository;
    @Override
    public InstructorDto createInstructor(InstructorDto instructorDto){

        Instructor instructor= InstructorMapper.mapToInstructor(instructorDto);
        Instructor savedInstructor = instructorRepository.save(instructor);

        return InstructorMapper.mapToInstructorDto(savedInstructor);
    }

    @Override
    public InstructorDto getInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("Instructor is not exist with given id:" + instructorId));

        return InstructorMapper.mapToInstructorDto(instructor);
    }

    @Override
    public List<InstructorDto> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();

        return instructors.stream().map((instructor) -> InstructorMapper.mapToInstructorDto(instructor))
                .collect(Collectors.toList());
    }

    @Override
    public InstructorDto updateInstructor(Long instructorId, InstructorDto updateInstructor) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new ResourceNotFoundException("Instructor is not exists by the given id:" + instructorId)
        );

        instructor.setName(updateInstructor.getName());
        instructor.setSurname(updateInstructor.getSurname());
        instructor.setEmail(updateInstructor.getEmail());
        instructor.setPassword(updateInstructor.getPassword());
        instructor.setPhone(updateInstructor.getPhone());
        Instructor updatedInstructorObj = instructorRepository.save(instructor);

        return InstructorMapper.mapToInstructorDto(updatedInstructorObj);
    }

    @Override
    public void deleteInstructor(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(
                () -> new ResourceNotFoundException("Instructor is not exists by the given id:" + instructorId)
        );
        instructorRepository.deleteById(instructorId);
    }


}
