package pl.minicode.targowiska.service.impl;

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

	public Page<Offer> findPaginatedOffers(Pageable pageable){
		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
       // int startItem = currentPage * pageSize;
        
        Pageable paging = PageRequest.of(currentPage, pageSize);
        
        Page<Offer> pagedResult = offerRepository.findAll(paging);
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
}
