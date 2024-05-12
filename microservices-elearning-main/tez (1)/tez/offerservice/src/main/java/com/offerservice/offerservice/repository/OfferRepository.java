package com.offerservice.offerservice.repository;


import com.educationplatform.userservice.entity.StudentCourse;
import com.offerservice.offerservice.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findByStudentId(Long studentId);

}
