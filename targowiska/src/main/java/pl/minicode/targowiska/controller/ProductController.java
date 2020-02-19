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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.service.INotificationService;
import pl.minicode.targowiska.service.IProductService;
import pl.minicode.targowiska.service.impl.FileSystemStorageService;
import pl.minicode.targowiska.type.ImageType;
import pl.minicode.targowiska.utils.CustomUtils;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class ProductController {

	@Autowired
	private IProductService productService;
	
	@Autowired
	private FileSystemStorageService fileSystemStorageService;
	
	@Autowired
    private INotificationService notifyService;

	@GetMapping("/admin/productlist")
	public String showProductListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<Product> productList = productService.findAll(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("productList", productList);

		int totalPages = productList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "admin-product-list"; // view
	}

	@GetMapping("/admin/productlist/newproduct")
	public String showAddNewProductForm(Product product) {
		return "admin-add-product";
	}

	@PostMapping("/admin/productlist/addproduct")
	public String addProduct(@Valid Product product, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
		boolean doSaveFile = file.getSize() != 0;
		
		if (result.hasErrors()) {
			return "admin-add-product";
		}
		
		if(doSaveFile) {
			String generatedFileName = CustomUtils.getGeneratedFileName(file);
			product.setImageName(generatedFileName);			
			fileSystemStorageService.storeImage(file, generatedFileName, ImageType.PRODUCT);			
		}

		productService.save(product);
		return "redirect:/admin/productlist";
	}
	
	
	@GetMapping("/admin/productlist/productdetails/{id}")
	public String getProductDetails(@PathVariable("id") Long id, Model model) {
		Product product = productService.findById(id);
		 model.addAttribute("product", product);
		return "redirect:/admin/productpricelist";
	}
	
	@GetMapping("/admin/productlist/delete/{id}")
	public String deleteProduct(@PathVariable("id") long id, Model model) {
		Product product = productService.findById(id);
	      
		productService.delete(product);
		notifyService.addInfoMessage("Product deleted successfully!!!");
	   return "redirect:/admin/productlist";
	}
}
