package pl.minicode.targowiska.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.minicode.targowiska.address.Address;
import pl.minicode.targowiska.address.IAddressService;
import pl.minicode.targowiska.common.validation.ImageCustomValidator;
import pl.minicode.targowiska.fileupload.StoredFileInfo;
import pl.minicode.targowiska.gallery.IImageGalleryService;
import pl.minicode.targowiska.gallery.ImageGallery;
import pl.minicode.targowiska.gallery.ImageType;
import pl.minicode.targowiska.gallery.ImagesGalleryDto;
import pl.minicode.targowiska.news.INewsService;
import pl.minicode.targowiska.news.News;
import pl.minicode.targowiska.service.impl.FileSystemStorageService;
import pl.minicode.targowiska.slider.ISliderService;
import pl.minicode.targowiska.slider.Slider;
import pl.minicode.targowiska.textpicture.ITextPictureService;
import pl.minicode.targowiska.textpicture.TextPicture;

@Controller
public class WelcomeController {

	@Autowired
	private INewsService newsService;

	@Autowired
	private ISliderService sliderService;
	
	@Autowired
	private ITextPictureService textPictureService;
	
	@Autowired
	private IAddressService addresservice;

	@Autowired
	private ImageCustomValidator validator;

	@Autowired
	private FileSystemStorageService fileSystemStorageService;
	
	@Autowired
	private IImageGalleryService imageGalleryService;
	
	private List<News> news;
	
	  // Login form
	@GetMapping("/login")
	  public String login() {
	    return "login";
	  }

	@GetMapping("/")
	public String showWelcomeSite(Model model) {
		List<News> dbNews = newsService.find4lastNews();

		if (dbNews.size() >= 4) {
		news = dbNews;
		model.addAttribute("news1", news.get(0));
		model.addAttribute("news2", news.get(1));
		model.addAttribute("news3", news.get(2));
		model.addAttribute("news4", news.get(3));
		}
		
		Address address = addresservice.getLastRowInTable();
		List<Slider> sliders = sliderService.findAll(Sort.by("id"));
		
		Page<ImageGallery> imageGaleries = imageGalleryService.findAll(PageRequest.of(0, 7));
		ImagesGalleryDto galleryDto = ImagesGalleryDto.createImageGalleryDtoForMainPage(imageGaleries);
		
		
		List<TextPicture> textPictureEntities = textPictureService.findByOwnerControllerClass(this.getClass().getSimpleName());

		TextPicture textPicture = null;
		if(!textPictureEntities.isEmpty()) {
			textPicture = textPictureEntities.get(0);
		}
		model.addAttribute("textPicture", textPicture);
		
		model.addAttribute("news", news);
		model.addAttribute("address", address);
		model.addAttribute("sliders", sliders);
		model.addAttribute("galleryDto", galleryDto);
		return "welcome2"; // view
	}

	@GetMapping("/admin/gui-content")
	public String showMainPageContent(Model model) {
		List<Slider> sliders = sliderService.findAll(Sort.by("id"));
		
		
		List<TextPicture> textPictureEntities = textPictureService.findByOwnerControllerClass(this.getClass().getSimpleName());

		TextPicture textPicture = null;
		if(!textPictureEntities.isEmpty()) {
			textPicture = textPictureEntities.get(0);
		}
		model.addAttribute("textPicture", textPicture);
		
		
		model.addAttribute("sliders", sliders);
		return "admin-main-page-content"; // view
	}

	@GetMapping("/admin/gui-content/edit-slide/{id}")
	public String editSlideSection(@PathVariable("id") Long id, Model model) {

		Slider slider = sliderService.findById(id);

		model.addAttribute("slider", slider);
		return "admin-edit-slide";
	}

	@PostMapping("/admin/gui-content/edit-slide/{id}")
	public String saveEditForm(@PathVariable("id") Long id, @ModelAttribute Slider slider, BindingResult result,
			Model model) {
		boolean doSaveFile = slider.getFile().getSize() != 0;
		if (result.hasErrors()) {
			return "admin-edit-slide";
		}
		if (doSaveFile) {
			validator.validate(slider, result);
			if (result.hasErrors()) {
				return "admin-edit-slide";
			}
			StoredFileInfo info = fileSystemStorageService.storeImage(slider.getFile(), ImageType.MAIN_GUI);
			slider.setFileName(info.getFileName());

		}
		slider.setId(id);
		sliderService.update(slider);

		return "redirect:/admin/gui-content";
	}

	@GetMapping("/admin/gui-content/edit-section/{id}")
	public String editMainSection(@PathVariable("id") Long id, Model model) {

		TextPicture textPicture = textPictureService.findById(id);

		model.addAttribute("textPicture", textPicture);
		return "admin-edit-main-section";
	}

	@PostMapping("/admin/gui-content/edit-section/{id}")
	public String saveMainSectionForm(@PathVariable("id") Long id, @ModelAttribute TextPicture textPicture, BindingResult result,
			Model model) {
		boolean doSaveFile = textPicture.getFile().getSize() != 0;
		if (result.hasErrors()) {
			return "admin-edit-main-section";
		}
		if (doSaveFile) {
			validator.validate(textPicture, result);
			if (result.hasErrors()) {
				return "admin-edit-main-section";
			}
			StoredFileInfo info = fileSystemStorageService.storeImage(textPicture.getFile(), ImageType.MAIN_GUI);
			textPicture.setImageName(info.getFileName());

		}
		textPicture.setId(id);
		textPicture.setOwnerControllerClass(this.getClass().getSimpleName());
		textPictureService.update(textPicture);

		return "redirect:/admin/gui-content";
	}
	
	@GetMapping("/admin/gui-content/edit-section")
	public String editMainSectionNew(TextPicture textPicture) {


		return "admin-edit-main-section";
	}
	
	@PostMapping("/admin/gui-content/add-section")
	public String saveMainSectionNewForm(@ModelAttribute TextPicture textPicture, BindingResult result,
			Model model) {
		boolean doSaveFile = textPicture.getFile() != null && textPicture.getFile().getSize() != 0;
		if (result.hasErrors()) {
			return "admin-edit-main-section";
		}
		if (doSaveFile) {
			validator.validate(textPicture, result);
			if (result.hasErrors()) {
				return "admin-edit-main-section";
			}
			StoredFileInfo info = fileSystemStorageService.storeImage(textPicture.getFile(), ImageType.MAIN_GUI);
			textPicture.setImageName(info.getFileName());
		}

		textPicture.setOwnerControllerClass(this.getClass().getSimpleName());
		textPictureService.save(textPicture);

		return "redirect:/admin/gui-content";
	}
}
