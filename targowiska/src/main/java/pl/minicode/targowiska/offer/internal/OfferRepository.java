package pl.minicode.targowiska.offer.internal;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.common.Status;
import pl.minicode.targowiska.offer.OfferType;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

	Page<Offer> findByOfferTypeAndStatus(Pageable pageable, OfferType offerType, Status status);
	
	Page<Offer> findByOfferTypeAndStatusNotIn(Pageable pageable, OfferType offerType, List<Status> statuses);
}
