package pl.minicode.targowiska.service;

import java.util.List;

import pl.minicode.targowiska.domain.News;

public interface INewsService extends IPageableCommonService<News> {

	List<News> find4lastNews();
}
