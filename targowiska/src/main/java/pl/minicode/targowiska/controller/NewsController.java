package pl.minicode.targowiska.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.minicode.targowiska.domain.News;
import pl.minicode.targowiska.repository.NewsRepository;

@Controller
public class NewsController {
	
	@Autowired
	private NewsRepository newsRepository;

	@GetMapping("/admin/newslist")
	public String showNewsListForm(Model model) {

		model.addAttribute("newsList", newsRepository.findAll());
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
