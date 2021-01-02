package pl.minicode.targowiska.contractor;

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

import pl.minicode.targowiska.common.INotificationService;
import pl.minicode.targowiska.common.PaginationUtils;
import pl.minicode.targowiska.fileupload.CustomUtils;
import pl.minicode.targowiska.fileupload.IFileSystemStorageService;
import pl.minicode.targowiska.fileupload.StoredFileInfo;
import pl.minicode.targowiska.gallery.ImageType;
import pl.minicode.targowiska.news.News;

@Controller
public class ContractorController {
	
	@Autowired
	private INotificationService notifyService;
	
	@Autowired
	private IContractorService contractorService;
	
	@Autowired
	private IFileSystemStorageService fileSystemStorageService;

	@GetMapping("/kontrahenci")
	public String showNewsList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);
		Page<Contractor> contractors = contractorService.findAll(PageRequest.of(currentPage - 1, pageSize));
		ContractorDto contractorDto = ContractorDto.createContractorDto(contractors);
		model.addAttribute("contractorDto", contractorDto);
//		Page<News> newsList = newsService.findActiveNews(PageRequest.of(currentPage - 1, pageSize));
//		if (ListUtils.isEmpty(newsList.getContent())) {
//			notifyService.addInfoMessage("No any news to show");
//		}
//		NewsDto newsDto = NewsDto.createNewsDto(newsList);
//		model.addAttribute("newsDto", newsDto);

		return "kontrahenci"; // view
	}
	
	@GetMapping("/kontrahenci-detale")
	public String showSingleNews (Model model, @RequestParam("id") Optional<Integer> id) {

		Contractor contractor = contractorService.findById(id.get().longValue());
		model.addAttribute("contractor", contractor);
		return "kontrahenci-detale"; // view
	}
	
	@GetMapping("/admin/contractorslist")
	public String showAdminNewsList(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		//Page<Contractor> contractors = contractorService.findAll();		
		List<Contractor> contractors = contractorService.findAll();
		
		model.addAttribute("contractorsList", contractors);

//		int totalPages = newsList.getTotalPages();
//		if (totalPages > 0) {
//			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//		if(ListUtils.isEmpty(newsList.getContent())) {
//			notifyService.addInfoMessage("No any news to show");
//			
//		}
		return "admin-contractors-list"; // view
	}
	
	@GetMapping("/admin/contractorslist/create")
	public String showAddNewContractorForm(Contractor contractor) {
		return "admin-add-contractor";
	}

	@PostMapping("/admin/contractorslist/save")
	public String addNewContractor(@Valid Contractor contractor, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		boolean doSaveFile = file.getSize() != 0;
		
		if (result.hasErrors()) {
			return "admin-add-contractor";
		}
		
		if(doSaveFile) {
			StoredFileInfo info = fileSystemStorageService.storeImage(file, ImageType.CONTRACTOR);			
			contractor.setImageLogoName(info.getFileName());	
			contractor.setMinImageLogoName(info.getMinFileName());
		}
		contractorService.save(contractor);
		return "redirect:/admin/contractorslist";
	}
}
