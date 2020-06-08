package pl.minicode.targowiska.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pl.minicode.targowiska.domain.News;

public interface INewsService extends IPageableCommonService<News> {

	List<News> find4lastNews();
	
	Page<News> findAdminNews(Pageable pageable);
}
