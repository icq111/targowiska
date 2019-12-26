package pl.minicode.targowiska.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.minicode.targowiska.domain.News;
import pl.minicode.targowiska.domain.Offer;
import pl.minicode.targowiska.repository.NewsRepository;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;

//	@GetMapping("/admin/newslist")
//	public String showNewsListForm(Model model) {
//
//		model.addAttribute("newsList", newsRepository.findAll());
//		return "admin-news-list"; // view
//	}

	@GetMapping("/admin/newslist")
	public String showNewsListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);
//		model.addAttribute("newsList", newsRepository.findAll());

		Page<News> newsList = newsRepository.findAll(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("newsList", newsList);

		int totalPages = newsList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "admin-news-list"; // view
	}

	@GetMapping("/admin/signup")
	public String showAddNewNewsForm(News news) {
		return "admin-add-news";
	}

	@PostMapping("/admin/addnews")
	public String addNews(@Valid News news, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-news";
		}

		newsRepository.save(news);
		model.addAttribute("newsList", newsRepository.findAll());
		return "redirect:newslist";
	}
}
