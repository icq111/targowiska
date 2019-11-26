package pl.minicode.targowiska.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.Offer;

@Repository
public interface OfferRepository extends PagingAndSortingRepository<Offer, Long> {

	//Page
}
