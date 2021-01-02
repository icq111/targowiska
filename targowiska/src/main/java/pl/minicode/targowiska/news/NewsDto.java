package pl.minicode.targowiska.news;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;

public class NewsDto {

	private Page<News> newsPages = Page.empty();
	private List<List<News>> newsRows = Collections.emptyList();
	private List<Integer> pageNumbers = Collections.emptyList();

	public static NewsDto createNewsDto(Page<News> listOfImages) {
		return new NewsDto(listOfImages);
	}

	public List<List<News>> getNewsRows() {
		return newsRows;
	}

	public List<Integer> pagesNumbers() {
		int totalPages = newsPages.getTotalPages();
		if (totalPages > 0) {
			pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
		return pageNumbers;
	}

	public Page<News> rowsPerPages() {
		return newsPages;
	}

	private NewsDto(Page<News> newsPages) {
		if (!newsPages.isEmpty()) {
			this.newsPages = newsPages;
			this.newsRows = new ArrayList<>();
			this.newsRows.add(new ArrayList<>());
		}
		devideNewsListToGrind();
	}

	private void devideNewsListToGrind() {
		AtomicInteger counter = new AtomicInteger();
		final int chunkSize = 3;
		for (News image : getNews()) {
			List<News> row = newsRows.get(counter.intValue());
			if (row.size() > 0 && row.size() % chunkSize == 0) {

				newsRows.add(new ArrayList<>());
				counter.incrementAndGet();
			}
			newsRows.get(counter.intValue()).add(image);
		}

	}

	private List<News> getNews() {
		return newsPages.getContent();
	}
}
