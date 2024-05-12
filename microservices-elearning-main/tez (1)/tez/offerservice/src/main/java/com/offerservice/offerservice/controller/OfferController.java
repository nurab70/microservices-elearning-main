package com.offerservice.offerservice.controller;

import com.offerservice.offerservice.exception.ResourceNotFoundException;
import com.offerservice.offerservice.dto.OfferDto;
import com.offerservice.offerservice.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import lombok.RequiredArgsConstructor;
import java.util.List;
import com.courserservice.courseservice.dto.CourseDto;
import com.educationplatform.userservice.dto.StudentDto;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.courserservice.courseservice.repository.CourseRepository;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class OfferController {

    @Autowired
    private final WebClient webClient;
    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferDto> createOffer(@RequestBody OfferDto offerDto) {


        CourseDto result1=null;
        StudentDto result2=null;
        try {
            result1 = webClient.get()
                    .uri("http://localhost:8082/api/courses/" + offerDto.getCourseId())
                    .retrieve()
                    .bodyToMono(CourseDto.class)
                    .block();
        }
        catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("Course is not exist with given id:" + offerDto.getCourseId());
        }
        try {
            result2 = webClient.get()
                    .uri("http://localhost:8081/api/students/" + offerDto.getStudentId())
                    .retrieve()
                    .bodyToMono(StudentDto.class)
                    .block();
        }
        catch (WebClientResponseException.NotFound ex) {
            throw new ResourceNotFoundException("Student is not exist with given id:" + offerDto.getStudentId());
        }

            if(result1!=null && result2!=null){
                OfferDto savedOffer = offerService.createOffer(offerDto);

                return new ResponseEntity<>(savedOffer, HttpStatus.CREATED);
            }
            else if(result1==null){
                throw new IllegalArgumentException("Course not available");
            }
            else{
                throw new IllegalArgumentException("Student not available");
            }
        }


    @GetMapping("{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable("id") Long offerId){
        OfferDto offerDto = offerService.getOfferById(offerId);
        return  ResponseEntity.ok(offerDto);
    }
    @GetMapping
    public ResponseEntity<List<OfferDto>> getAllOffers(){
        List<OfferDto> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }
    @PutMapping({"id"})
    public ResponseEntity<OfferDto> updateOffer(@PathVariable("id") Long offerId,
                                                  @RequestBody OfferDto updatedOffer){
        OfferDto offerDto = offerService.updateOffer(offerId, updatedOffer);
        return ResponseEntity.ok(offerDto);
    }
    @DeleteMapping("/{id}") // Burada da id için süslü parantez içinde olmalı
    public ResponseEntity<String> deleteOffer(@PathVariable("id") Long offerId){
        offerService.deleteOffer(offerId);
        return ResponseEntity.ok("Offer deleted successfully!");
    }
}