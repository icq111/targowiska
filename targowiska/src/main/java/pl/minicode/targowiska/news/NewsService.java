package pl.minicode.targowiska.news;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pl.minicode.targowiska.common.Status;

@Service
public class NewsService implements INewsService {
	
	@Autowired
	private NewsRepository newsRepository;

	@Override
	public List<News> findAll() {
		return (List<News>) newsRepository.findAll();
	}

	@Override
	public News save(News entity) {
		return newsRepository.save(entity);
	}

	@Override
	public News update(News entity) {
		return save(entity);
	}

	@Override
	public News delete(News entity) {
		entity.setStatus(Status.DELETED);
		return save(entity);
	}

	@Override
	public News findById(Long id) throws EntityNotFoundException {
		return  newsRepository.findById(id).orElseThrow(()-> throwEntityNotFoundException(id));
	}

	@Override
	public Page<News> findAll(Pageable pageable) {
		return newsRepository.findAll(pageable);
	}

	@Override
	public EntityNotFoundException throwEntityNotFoundException(Long id) {
		return new EntityNotFoundException("NEWS: Not found entity with given id:" + id);
	}

	@Override
	public List<News> find4lastNews() {
		return newsRepository.find4LastNews();
	}
	
	public Page<News> findActiveNews(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();

		Pageable paging = PageRequest.of(currentPage, pageSize);

		Page<News> pagedResult = newsRepository.findByStatusNotIn(Arrays.asList(Status.DELETED), paging);
		return pagedResult;
	}

	@Override
	public List<News> find4LastImportantNews() {
		return newsRepository.find4LastImportantNews();
	}

}
