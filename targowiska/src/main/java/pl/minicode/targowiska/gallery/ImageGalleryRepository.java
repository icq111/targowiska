package pl.minicode.targowiska.gallery;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageGalleryRepository extends PagingAndSortingRepository<ImageGallery, Long> {

}
