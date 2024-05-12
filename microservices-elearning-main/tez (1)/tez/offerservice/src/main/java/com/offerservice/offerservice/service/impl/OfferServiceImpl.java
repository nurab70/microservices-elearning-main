package com.offerservice.offerservice.service.impl;

import com.offerservice.offerservice.dto.OfferDto;
import com.offerservice.offerservice.entity.Offer;
import com.offerservice.offerservice.exception.ResourceNotFoundException;
import com.offerservice.offerservice.mapper.OfferMapper;
import com.offerservice.offerservice.repository.OfferRepository;
import com.offerservice.offerservice.service.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.courserservice.courseservice.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {

    private OfferRepository offerRepository;


    @Override
    public OfferDto createOffer(OfferDto offerDto) {
        Offer offer= OfferMapper.mapToOffer(offerDto);
        Offer savedOffer = offerRepository.save(offer);


        return OfferMapper.mapToOfferDto(savedOffer);
    }

    @Override
    public OfferDto getOfferById(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer is not exist with given id:" + offerId));

        return OfferMapper.mapToOfferDto(offer);
    }

    @Override
    public List<OfferDto> getAllOffers() {
        List<Offer> offers = offerRepository.findAll();

        return offers.stream().map((offer) -> OfferMapper.mapToOfferDto(offer))
                .collect(Collectors.toList());
    }

    @Override
    public OfferDto updateOffer(Long offerId, OfferDto updatedoffer) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(
                () -> new ResourceNotFoundException("offer is not exists by the given id:" + offerId));
        offer.setMessage(updatedoffer.getMessage());
        offer.setType(updatedoffer.getType());
        offer.setPrice(updatedoffer.getPrice());
        offer.setCourseId(updatedoffer.getCourseId());
        offer.setStudentId(updatedoffer.getStudentId());



        Offer updatedofferObj = offerRepository.save(offer);

        return OfferMapper.mapToOfferDto(updatedofferObj);

    }

    @Override
    public void deleteOffer(Long offerId) {
        Offer offer = offerRepository.findById(offerId)
                .orElseThrow(() -> new ResourceNotFoundException("Offer is not exist with given id:" + offerId));
        offerRepository.deleteById(offerId);

    }


}
