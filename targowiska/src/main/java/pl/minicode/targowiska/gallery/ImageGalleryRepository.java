package pl.minicode.targowiska.gallery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageGalleryRepository extends PagingAndSortingRepository<ImageGallery, Long> {

	@Query(nativeQuery= true, value ="select * from image_gallery n order by insert_stamp desc limit :numberOfRows ")
	Page<ImageGallery> getLastXRows(@Param("numberOfRows") int numberOfRows, Pageable pageable);
}
