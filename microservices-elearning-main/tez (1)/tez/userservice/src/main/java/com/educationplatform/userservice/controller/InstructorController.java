package com.educationplatform.userservice.controller;

import com.educationplatform.userservice.service.InstructorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import com.educationplatform.userservice.dto.InstructorDto;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
    private final InstructorService instructorService;
    private final WebClient.Builder webClientBuilder;

    @PostMapping
    public ResponseEntity<InstructorDto> createInstructor(@RequestBody InstructorDto instructorDto) {
        InstructorDto savedInstructor = instructorService.createInstructor(instructorDto);
        return new ResponseEntity<>(savedInstructor, HttpStatus.CREATED);
    }

    //Build Get Employee REST API
    @GetMapping("{id}")
    public ResponseEntity<InstructorDto> getInstructorById(@PathVariable("id") Long instructorId) {
        InstructorDto instructorDto = instructorService.getInstructorById(instructorId);
        return ResponseEntity.ok(instructorDto);
    }

    @GetMapping
    public ResponseEntity<List<InstructorDto>> getAllInstructors() {
        List<InstructorDto> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    @PutMapping({"id"})
    public ResponseEntity<InstructorDto> updateInstructor(@PathVariable("id") Long instructorId,
                                                    @RequestBody InstructorDto updatedInstructor) {
        InstructorDto instructorDto = instructorService.updateInstructor(instructorId, updatedInstructor);
        return ResponseEntity.ok(instructorDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInstructor(@PathVariable("id") Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        return ResponseEntity.ok("Instructor deleted successfully!.");
    }

}
