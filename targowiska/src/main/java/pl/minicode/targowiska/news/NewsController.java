package pl.minicode.targowiska.news;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.common.INotificationService;
import pl.minicode.targowiska.common.ImageProperties;
import pl.minicode.targowiska.common.PaginationUtils;
import pl.minicode.targowiska.common.validation.ImageCustomValidator;
import pl.minicode.targowiska.fileupload.CustomUtils;
import pl.minicode.targowiska.fileupload.StoredFileInfo;
import pl.minicode.targowiska.gallery.ImageType;
import pl.minicode.targowiska.product.Product;
import pl.minicode.targowiska.service.impl.FileSystemStorageService;
import pl.minicode.targowiska.textpicture.TextPicture;

@Controller
public class NewsController {

	@Autowired
	private INewsService newsService;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;
	
	@Autowired
    private INotificationService notifyService;
	
	@Autowired
	private ImageCustomValidator validator;
	
	
	@GetMapping("/aktualnosci")
	public String showNewsList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);
		
		List<News> importantNews = newsService.find4LastImportantNews();

		Page<News> newsList = newsService.findActiveNews(PageRequest.of(currentPage - 1, pageSize));
		NewsDto newsDto = NewsDto.createNewsDto(newsList);
		model.addAttribute("newsDto", newsDto);
		model.addAttribute("importantNews", importantNews);
		return "aktualnosci"; // view
	}

	
	@GetMapping("/admin/newslist")
	public String showAdminNewsList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<News> newsList = newsService.findActiveNews(PageRequest.of(currentPage - 1, pageSize));		
		//List<NewsDto> newsDtoList = c
		
		
		model.addAttribute("newsList", newsList);

		int totalPages = newsList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		if(ListUtils.isEmpty(newsList.getContent())) {
			notifyService.addInfoMessage("Brak aktualności do wyświetlenia. Dodaj nowe aktualności");
			
		}
		return "admin-news-list"; // view
	}
	
	@GetMapping("/aktualnosci-detale/{id}")
	public String showSingleNews (Model model, @PathVariable("id") Integer id) {

		News news = newsService.findById(id.longValue());
		model.addAttribute("news", news);
		return "aktualnosci-detal"; // view
	}
	

	@GetMapping("/admin/newslist/create")
	public String showAddNewNewsForm(News news) {
		return "admin-add-news";
	}

	@PostMapping("/admin/news/save")
	public String addNews(@Valid News news, BindingResult result, Model model) {
		boolean doSaveFile = news.getFile().getSize() != 0;
		if (result.hasErrors()) {
			notifyService.addErrorMessage("Nie zapisano! Popraw błędy w formularzu");
			return "admin-add-news";
		}
		if(doSaveFile) {
			validator.validate(news, result);
			if (result.hasErrors()) {
				notifyService.addErrorMessage("Nie zapisano! Popraw błędy w formularzu");
				return "admin-add-news";
			}
			StoredFileInfo info = fileSystemStorageService.storeImage(news.getFile(), ImageType.NEWS);			
			news.setImageName(info.getFileName());
			news.setMinImageName(info.getMinFileName());
		}
		newsService.save(news);
		return "redirect:/admin/newslist";
	}
	
	
	//------------------editing----------------
	
	@GetMapping("/admin/news/edit/{id}")
	public String editNews(@PathVariable("id") long id, Model model) {
		News news = newsService.findById(id);
	    
	    model.addAttribute("news", news);
	    return "admin-add-news";
	}
	
	@PostMapping("/admin/news/save/{id}")
	public String saveEditedNews(@PathVariable("id")  Optional<Long> id, @Valid News news, BindingResult result, Model model) {
		boolean doSaveFile = news.getFile().getSize() != 0;
		if (result.hasErrors()) {
			return "admin-add-news";
		}
		news.setId(id.get());
		if(doSaveFile) {
			validator.validate(news, result);
			if (result.hasErrors()) {
				notifyService.addErrorMessage("Nie zapisano! Popraw błędy w formularzu");
				return "admin-add-news";
			}
			StoredFileInfo info = fileSystemStorageService.storeImage(news.getFile(), ImageType.NEWS);			
			news.setImageName(info.getFileName());
			news.setMinImageName(info.getMinFileName());
		}
		newsService.save(news);
		return "redirect:/admin/newslist";
	}
	
	//--------------------deleting---------------------------------------------
	@GetMapping("/admin/news/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id, Model model) {
		News news = newsService.findById(id);
	      
		newsService.delete(news);
		notifyService.addInfoMessage("Aktualność usunięta poprawnie !!!");
	   return "redirect:/admin/newslist";
	}
}
