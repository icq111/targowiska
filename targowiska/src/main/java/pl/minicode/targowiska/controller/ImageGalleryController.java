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

import pl.minicode.targowiska.domain.ImageGallery;
import pl.minicode.targowiska.service.IFileSystemStorageService;
import pl.minicode.targowiska.service.IImageGalleryService;
import pl.minicode.targowiska.type.ImageType;
import pl.minicode.targowiska.type.Status;
import pl.minicode.targowiska.utils.CustomUtils;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class ImageGalleryController {
	
	@Autowired
	private IImageGalleryService imageGalleryService;
	
	@Autowired
	private IFileSystemStorageService fileSystemStorageService; 

	@GetMapping("/admin/gallery")
	public String showGalleryPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(12);

		Page<ImageGallery> imagesList = imageGalleryService.findAll(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("imagesList", imagesList);	

		int totalPages = imagesList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "admin-image-gallery-list"; // view
	}
	
	@GetMapping("/admin/gallery/create")
	public String showCreateForm(Model model) {

		return "admin-add-image";
	}
	
	@PostMapping("/admin/gallery/save")
	public String savePriceList(ImageGallery imageGallery, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		boolean doSaveFile = file.getSize() != 0;
		
		if (result.hasErrors()) {
			return "admin-add-image";
		}
		
		if(doSaveFile) {
			String generatedFileName = CustomUtils.getGeneratedFileName(file);
			imageGallery.setImageName(generatedFileName);			
			fileSystemStorageService.store(file, generatedFileName, ImageType.GALLERY);	
			
			ImageGallery gallery = new ImageGallery();
			gallery.setImageName(generatedFileName);
			gallery.setStatus(Status.ACTIVE);
		}
		imageGalleryService.save(imageGallery);
		return "redirect:/admin/gallery";
	}
}
