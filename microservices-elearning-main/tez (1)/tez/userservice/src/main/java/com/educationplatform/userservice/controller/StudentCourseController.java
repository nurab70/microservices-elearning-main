package com.educationplatform.userservice.controller;
import java.util.List;

import com.courserservice.courseservice.exception.ResourceNotFoundException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.educationplatform.userservice.service.StudentCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.educationplatform.userservice.dto.StudentCourseDto;
import com.courserservice.courseservice.dto.CourseDto;
import com.educationplatform.userservice.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@RestController
@RequestMapping("/api/studentCourses")
public class StudentCourseController {
    @Autowired
    private final WebClient webClient;
    private final StudentCourseService studentCourseService;



    @PostMapping
    public ResponseEntity<StudentCourseDto> createStudentCourse(@RequestBody StudentCourseDto studentCourseDto) {

        CourseDto result1 = null;
        StudentDto result2 = null;
        try {
            result2 = webClient.get()
                    .uri("http://localhost:8081/api/students/" + studentCourseDto.getStudentId())
                    .retrieve()
                    .bodyToMono(StudentDto.class)
                    .block();
        }
        catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("Student is not exist with given id:" + studentCourseDto.getStudentId());
        }
       try {
            result1 = webClient.get()
                    .uri("http://localhost:8082/api/courses/" + studentCourseDto.getCourseId())
                    .retrieve()
                    .bodyToMono(CourseDto.class)
                    .block();
        }
        catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("Course is not exist with given id:" + studentCourseDto.getCourseId());
        }


        if(result1!=null && result2!=null){
            StudentCourseDto savedStudentCourse = studentCourseService.createStudentCourse(studentCourseDto);

            return new ResponseEntity<>(savedStudentCourse, HttpStatus.CREATED);
        }
        else if(result1==null){
            throw new IllegalArgumentException("Course not available");
        }
        else{
            throw new IllegalArgumentException("Student not available");
        }
    }
    @GetMapping
    public ResponseEntity<List<StudentCourseDto>> getAllStudentCourses() {
        List<StudentCourseDto> studentCourses = studentCourseService.getAllStudentCourses();
        return ResponseEntity.ok(studentCourses);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentCourseDto> getStudentCourseById(@PathVariable("id") Long studentCourseId) {
        StudentCourseDto studentCourseDto = studentCourseService.getStudentCourseById(studentCourseId);
        return ResponseEntity.ok(studentCourseDto);
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<StudentCourseDto>> getStudentCourseByStudentId(@PathVariable("studentId") Long studentId) {

        StudentDto result = null;
        try {
            result = webClient.get()
                    .uri("http://localhost:8081/api/students/" + studentId)
                    .retrieve()
                    .bodyToMono(StudentDto.class)
                    .block();

            if(result != null){
                List<StudentCourseDto> studentCourses = studentCourseService.getStudentCourseByStudentId(studentId);
                return ResponseEntity.ok(studentCourses);
            }
            else{
                throw new IllegalArgumentException("Student not available");
            }
        }
        catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("Student is not exist with given id:" + studentId);
        }
    }

    @PutMapping({"id"})
    public ResponseEntity<StudentCourseDto> updateStudentCourse(@PathVariable("id") Long studentCourseId,
                                                                @RequestBody StudentCourseDto updatedStudentCourse) {
        StudentCourseDto studentCourseDto = studentCourseService.updateStudentCourse(studentCourseId, updatedStudentCourse);
        return ResponseEntity.ok(studentCourseDto);
    }

    @DeleteMapping("/{id}") // Burada da id için süslü parantez içinde olmalı
    public ResponseEntity<String> deleteStudentCourse(@PathVariable("id") Long studentCourseId){
        studentCourseService.deleteStudentCourse(studentCourseId);
        return ResponseEntity.ok("StudentCourse deleted successfully!");
    }

}

