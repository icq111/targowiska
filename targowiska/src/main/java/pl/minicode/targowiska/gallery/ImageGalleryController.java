package pl.minicode.targowiska.gallery;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.common.PaginationUtils;
import pl.minicode.targowiska.common.Status;
import pl.minicode.targowiska.fileupload.CustomUtils;
import pl.minicode.targowiska.fileupload.IFileSystemStorageService;
import pl.minicode.targowiska.fileupload.StoredFileInfo;

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

		Page<ImageGallery> imagesList = imageGalleryService.findAll(PageRequest.of(currentPage - 1, pageSize, Sort.by("height").descending().and(Sort.by("insertStamp")).descending()));
		ImagesGalleryDto imagesGalleryDto = ImagesGalleryDto.createImageGalleryDto(imagesList);
		
//		final int chunkSize = 4;
//		final AtomicInteger counter = new AtomicInteger();
//
//		for (ImageGallery ig : imagesList.getContent()) {
//		    if (counter.getAndIncrement() % chunkSize == 0) {
//		    	imagesGalleryDto = new ImagesGalleryDto();
//				imagesGalleryList.add(imagesGalleryDto);
//		    }
//		    imagesGalleryList.get(imagesGalleryList.size() - 1).addImageGallery(ig);
//		}

		
		
//		model.addAttribute("imagesGalleryList", imagesGalleryList);	
//
//		int totalPages = imagesList.getTotalPages();
//		if (totalPages > 0) {
//			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
		model.addAttribute("imagesGalleryDto", imagesGalleryDto);
		return "admin-image-gallery-list"; // view
	}
	
	@GetMapping("/admin/gallery/create")
	public String showCreateForm(Model model) {

		return "admin-add-image";
	}
	
	@PostMapping("/admin/gallery/save")
	public String savePriceList(ImageGallery imageGallery, BindingResult result, Model model, @RequestParam("files") List<MultipartFile> files) {
		boolean doSaveFile = !ListUtils.isEmpty(files);
		
		if (result.hasErrors()) {
			return "admin-add-image";
		}
		
		if (doSaveFile) {
			for (MultipartFile f : files) {
				
				StoredFileInfo storedFileInfo = fileSystemStorageService.storeImage(f, ImageType.GALLERY);

				
				ImageGallery gallery = new ImageGallery();
				gallery.setImageName(storedFileInfo.getFileName());
				gallery.setStatus(Status.ACTIVE);
				gallery.setHeight(storedFileInfo.getDimension().height);
				gallery.setWidth(storedFileInfo.getDimension().width);
				imageGalleryService.save(gallery);
			}
		}
		return "redirect:/admin/gallery";
	}
}
