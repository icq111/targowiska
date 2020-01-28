package pl.minicode.targowiska.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.minicode.targowiska.domain.ImageGallery;
import pl.minicode.targowiska.service.IImageGalleryService;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class ImageGalleryController {
	
	@Autowired
	private IImageGalleryService imageGalleryService;

	@GetMapping("/admin/gallery")
	public String showGalleryPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<ImageGallery> imagesList = imageGalleryService.findAll(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("imagesList", imagesList);	

		int totalPages = imagesList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "admin-images-list"; // view
	}
}
