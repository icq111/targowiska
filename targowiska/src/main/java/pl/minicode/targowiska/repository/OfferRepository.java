package pl.minicode.targowiska.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.Offer;
import pl.minicode.targowiska.type.OfferType;
import pl.minicode.targowiska.type.Status;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

	Page<Offer> findByOfferTypeAndStatus(Pageable pageable, OfferType offerType, Status status);
	
	Page<Offer> findByOfferTypeAndStatusNotIn(Pageable pageable, OfferType offerType, List<Status> statuses);
}
