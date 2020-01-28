package pl.minicode.targowiska.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import pl.minicode.targowiska.domain.ImageGallery;

@Repository
public interface ImageGalleryRepository extends PagingAndSortingRepository<ImageGallery, Long> {

}
