package com.offerservice.offerservice.mapper;

import com.offerservice.offerservice.dto.OfferDto;
import com.offerservice.offerservice.entity.Offer;
public class OfferMapper {

    public  static OfferDto mapToOfferDto(Offer offer) {
        return new OfferDto(
                offer.getId(),
                offer.getMessage(),
                offer.getType(),
                offer.getPrice(),
                offer.getCourseId(),
                offer.getStudentId()

        );
    }

    public static Offer mapToOffer(OfferDto offerDto){
        return new Offer(
                offerDto.getId(),
                offerDto.getMessage(),
                offerDto.getType(),
                offerDto.getPrice(),
                offerDto.getCourseId(),
                offerDto.getStudentId()
        );
    }

}
