package pl.minicode.targowiska.slider;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SliderRepository extends PagingAndSortingRepository<Slider, Long> {

}
