package com.educationplatform.userservice.service.impl;

import com.educationplatform.userservice.dto.StudentCourseDto;
import com.educationplatform.userservice.entity.StudentCourse;
import com.educationplatform.userservice.exception.ResourceNotFoundException;
import com.educationplatform.userservice.mapper.StudentCourseMapper;
import com.educationplatform.userservice.repository.StudentCourseRepository;
import com.educationplatform.userservice.service.StudentCourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentCourseServiceImpl implements StudentCourseService{

    private StudentCourseRepository studentCourseRepository;
    @Override
    public StudentCourseDto createStudentCourse(StudentCourseDto studentCourseDto) {
        Long studentId = studentCourseDto.getStudentId();
        Long courseId = studentCourseDto.getCourseId();

        // Aynı studentId ve courseId' e sahip kaydı kontrol et
        List<StudentCourse> existingRecords = studentCourseRepository.findByStudentIdAndCourseId(studentId, courseId);
        if (!existingRecords.isEmpty()) {
            // Eğer aynı kayıt varsa uygun bir hata mesajı döndür
            throw new ResourceNotFoundException("This student is already enrolled in this course.");
        }

        StudentCourse studentCourse = StudentCourseMapper.mapToStudentCourse(studentCourseDto);
        StudentCourse savedStudentCourse = studentCourseRepository.save(studentCourse);

        return StudentCourseMapper.mapToStudentCourseDto(savedStudentCourse);
    }



    @Override
    public StudentCourseDto getStudentCourseById(Long studentCourseId) {


        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId)
                .orElseThrow(() -> new ResourceNotFoundException("StudentCourse is not exist with given id:" + studentCourseId));

        return StudentCourseMapper.mapToStudentCourseDto(studentCourse);
    }

    @Override
    public List<StudentCourseDto> getStudentCourseByStudentId(Long studentId) {


        List<StudentCourse> studentCourses = studentCourseRepository.findByStudentId(studentId);

        if (studentCourses == null || studentCourses.isEmpty()) {
            throw new ResourceNotFoundException("Student courses not found for student with id: " + studentId);
        }
        return  studentCourses.stream().map((studentCourse) -> StudentCourseMapper.mapToStudentCourseDto(studentCourse))
                .collect(Collectors.toList());
    }


    @Override
    public List<StudentCourseDto> getAllStudentCourses() {
        List<StudentCourse> studentCourses = studentCourseRepository.findAll();

        return studentCourses.stream().map((studentCourse) -> StudentCourseMapper.mapToStudentCourseDto(studentCourse))
                .collect(Collectors.toList());
    }

    @Override
    public StudentCourseDto updateStudentCourse(Long studentCourseId, StudentCourseDto updateStudentCourse) {
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow(
                () -> new ResourceNotFoundException("StudentCourse is not exists by the given id:" + studentCourseId)
        );

        studentCourse.setStudentId(updateStudentCourse.getStudentId());
        studentCourse.setCourseId(updateStudentCourse.getCourseId());

        StudentCourse updatedStudentCourseObj = studentCourseRepository.save(studentCourse);

        return StudentCourseMapper.mapToStudentCourseDto(updatedStudentCourseObj);
    }

    @Override
    public void deleteStudentCourse(Long studentCourseId) {
        StudentCourse studentCourse = studentCourseRepository.findById(studentCourseId).orElseThrow(
                () -> new ResourceNotFoundException("StudentCourse is not exists by the given id:" + studentCourseId)
        );
        studentCourseRepository.deleteById(studentCourseId);
    }
}
