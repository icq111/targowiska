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
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.domain.News;
import pl.minicode.targowiska.repository.NewsRepository;
import pl.minicode.targowiska.service.INotificationService;
import pl.minicode.targowiska.service.impl.FileSystemStorageService;
import pl.minicode.targowiska.type.ImageType;
import pl.minicode.targowiska.utils.CustomUtils;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class NewsController {

	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;
	
	@Autowired
    private INotificationService notifyService;

	@GetMapping("/admin/newslist")
	public String showNewsListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<News> newsList = newsRepository.findAll(PageRequest.of(currentPage - 1, pageSize));		
		model.addAttribute("newsList", newsList);

		int totalPages = newsList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		if(ListUtils.isEmpty(newsList.getContent())) {
			notifyService.addErrorMessage("No any news to show");
			
		}
		return "admin-news-list"; // view
	}

	@GetMapping("/admin/newslist/signup")
	public String showAddNewNewsForm(News news) {
		return "admin-add-news";
	}

	@PostMapping("/admin/newslist/addnews")
	public String addNews(@Valid News news, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		boolean doSaveFile = file.getSize() != 0;
		
		if (result.hasErrors()) {
			return "admin-add-news";
		}
		
		if(doSaveFile) {
			String generatedFileName = CustomUtils.getGeneratedFileName(file);
			news.setImageName(generatedFileName);			
			fileSystemStorageService.store(file, generatedFileName, ImageType.NEWS);			
		}
		newsRepository.save(news);
		return "redirect:/admin/newslist";
	}
}
