package pl.minicode.targowiska.productcategory;

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

import pl.minicode.targowiska.common.INotificationService;
import pl.minicode.targowiska.common.PaginationUtils;
import pl.minicode.targowiska.fileupload.CustomUtils;
import pl.minicode.targowiska.gallery.ImageType;
import pl.minicode.targowiska.product.Product;
import pl.minicode.targowiska.service.impl.FileSystemStorageService;
//NOT USED TEMPORARY
//@Controller
public class ProductCategoryController {
	
//	@Autowired
//	private ProductCategoryService productCategoryService;
//	
//	@Autowired
//	private FileSystemStorageService fileSystemStorageService;
//	
//	@Autowired
//    private INotificationService notifyService;
//
//	@GetMapping("/admin/productcategorylist")
//	public String showProductCategoryListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
//			@RequestParam("size") Optional<Integer> size) {
//		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
//		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);
//
//		Page<ProductCategory> productCategoryList = productCategoryService.findAll(PageRequest.of(currentPage - 1, pageSize));
//
//		model.addAttribute("productCategoryList", productCategoryList);
//
//		int totalPages = productCategoryList.getTotalPages();
//		if (totalPages > 0) {
//			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
//			model.addAttribute("pageNumbers", pageNumbers);
//		}
//
//		return "admin-product-category-list"; // view
//	}
//	
//	@GetMapping("/admin/productcategorylist/newproductcategory")
//	public String showAddNewProductCategoryForm(ProductCategory productCategory) {
//		return "admin-add-product-category";
//	}
//
//	@PostMapping("/admin/productcategorylist/addproductcategory")
//	public String addProductCategory(@Valid ProductCategory productCategory, BindingResult result, Model model, @RequestParam("file") MultipartFile file) {
//		boolean doSaveFile = file.getSize() != 0;
//		
//		if (result.hasErrors()) {
//			return "admin-add-product-category";
//		}
//		
//		if(doSaveFile) {
//			String generatedFileName = CustomUtils.getGeneratedFileName(file);
//			productCategory.setImageName(generatedFileName);			
//			fileSystemStorageService.storeImage(file, generatedFileName, ImageType.PRODUCT);			
//		}
//
//		productCategoryService.save(productCategory);
//		return "redirect:/admin/productcategorylist";
//	}
}
