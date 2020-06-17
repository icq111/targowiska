package pl.minicode.targowiska.service.impl;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.domain.Offer;
import pl.minicode.targowiska.repository.OfferRepository;
import pl.minicode.targowiska.type.OfferType;
import pl.minicode.targowiska.type.Status;

@Service
public class OfferService {
	
	@Autowired
	private OfferRepository offerRepository;

//	public Page<Offer> findPaginatedOffers(Pageable pageable){
//		int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        
//        Pageable paging = PageRequest.of(currentPage, pageSize);
//        
//        Page<Offer> pagedResult = offerRepository.findAll(paging);
//        return pagedResult;
//	}
	
	private Page<Offer> findAdminPaginatedOffersByType(Pageable pageable, OfferType offerType){
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        
        Pageable paging = PageRequest.of(currentPage, pageSize);
        
        Page<Offer> pagedResult = offerRepository.findByOfferTypeAndStatusNotIn(paging, offerType, Arrays.asList(Status.DELETED));
        return pagedResult;
	}
	
	public Page<Offer> findPaginatedInternalOffers(Pageable pageable){  
        Page<Offer> pagedResult = findAdminPaginatedOffersByType(pageable, OfferType.INTERNAL);
        return pagedResult;
	}

	public Page<Offer> findPaginatedExternalOffers(Pageable pageable){
		Page<Offer> pagedResult = findAdminPaginatedOffersByType(pageable, OfferType.EXTERNAL);
        return pagedResult;
	}
	
	public void saveInternalOffer(@Valid Offer offer) {
		if(offer.getOfferType() == null) {
			offer.setOfferType(OfferType.INTERNAL);
		}
		
		if(offer.getStatus() == null) {
			offer.setStatus(Status.INACTIVE);
		}
		offerRepository.save(offer);
	}
	
	public void saveExternalOffer(@Valid Offer offer) {
		if(offer.getOfferType() == null) {
			offer.setOfferType(OfferType.EXTERNAL);
		}
		
		if(offer.getStatus() == null) {
			offer.setStatus(Status.INACTIVE);
		}
		offerRepository.save(offer);
	}
}
