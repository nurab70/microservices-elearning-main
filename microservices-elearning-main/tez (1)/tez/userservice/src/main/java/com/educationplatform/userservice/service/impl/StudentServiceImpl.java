package com.educationplatform.userservice.service.impl;

import com.educationplatform.userservice.dto.StudentDto;
import com.educationplatform.userservice.entity.Student;
import com.educationplatform.userservice.entity.StudentCourse;
import com.educationplatform.userservice.exception.ResourceNotFoundException;
import com.educationplatform.userservice.mapper.StudentMapper;
import com.educationplatform.userservice.repository.StudentRepository;
import com.educationplatform.userservice.service.StudentService;
import com.offerservice.offerservice.entity.Offer;
import com.offerservice.offerservice.repository.OfferRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import com.educationplatform.userservice.repository.StudentCourseRepository;
//import com.offerservice.offerservice.*;

import java.util.List;
import java.util.stream.Collectors;




@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private StudentCourseRepository studentCourseRepository;


    @Override
    public StudentDto createStudent(StudentDto studentDto){

        Student student= StudentMapper.mapToStudent(studentDto);
        Student savedStudent = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student is not exist with given id:" + studentId));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream().map((student) -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto updateStudent) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exists by the given id:" + studentId)
        );

        student.setName(updateStudent.getName());
        student.setSurname(updateStudent.getSurname());
        student.setEmail(updateStudent.getEmail());
        student.setPassword(updateStudent.getPassword());
        student.setPhone(updateStudent.getPhone());
        Student updatedStudentObj = studentRepository.save(student);

        return StudentMapper.mapToStudentDto(updatedStudentObj);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new ResourceNotFoundException("Student is not exists by the given id:" + studentId)
        );

        // Öğrenciye ait tüm ders kayıtlarını bul
        List<StudentCourse> studentCourses = studentCourseRepository.findByStudentId(studentId);
        //List<Offer> offer = offerRepository.findByStudentId(studentId);
        // Öğrenciye ait tüm ders kayıtlarını sil
        studentCourseRepository.deleteAll(studentCourses);
        //offerRepository.deleteAll(offer);
        studentRepository.deleteById(studentId);
    }


}
