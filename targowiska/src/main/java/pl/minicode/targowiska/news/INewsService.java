package pl.minicode.targowiska.news;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface INewsService extends IPageableCommonService<News> {

	List<News> find4lastNews();
	
	Page<News> findAdminNews(Pageable pageable);
}
