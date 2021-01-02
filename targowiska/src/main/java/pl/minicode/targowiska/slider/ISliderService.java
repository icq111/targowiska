package pl.minicode.targowiska.slider;

import java.util.List;

import org.springframework.data.domain.Sort;

import pl.minicode.targowiska.common.ICommonService;


public interface ISliderService extends ICommonService<Slider> {
	
	List<Slider> findAll(Sort sort);

}
