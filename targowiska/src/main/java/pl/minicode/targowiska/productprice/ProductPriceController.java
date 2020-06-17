package pl.minicode.targowiska.productprice;

import java.math.BigDecimal;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.common.PaginationUtils;
import pl.minicode.targowiska.product.IProductService;
import pl.minicode.targowiska.product.Product;
import pl.minicode.targowiska.product.ProductValidatorService;
import pl.minicode.targowiska.product.ProductsDto;

@Controller
public class ProductPriceController {
	
	@Autowired
	IProductService productService;
	
	@Autowired
	ProductValidatorService productValidatorService;

	@GetMapping("/admin/productpricelist")
	public String showProductListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		Date priceListDate = new Date();
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<Product> productPriceList = productService.findAll(PageRequest.of(currentPage - 1, pageSize));
		if(!ListUtils.isEmpty(productPriceList.getContent())) {
			priceListDate = productPriceList.getContent().iterator().next().getProductPriceUpdateStamp();
		}
		model.addAttribute("productPriceList", productPriceList);
		model.addAttribute("priceListDate" , priceListDate);		

		int totalPages = productPriceList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("isAddProductPricesAllowed", productValidatorService.isAddProductPricesAllowed());
		
		return "admin-product-price-list"; // view
	}
	
	
	
	@GetMapping("/admin/productpricelist/create")
	public String showCreateForm(Model model) {
		ProductsDto productsDto = new ProductsDto();
		List<Product> products = productService.findAll();

		if (!ListUtils.isEmpty(products)) {
			for (Product product : products) {
				product.setOldProductPrice(product.getProductPrice() == null ? BigDecimal.ZERO : product.getProductPrice());
				productsDto.addProduct(product);
			}
		}

		model.addAttribute("productsDto", productsDto);
		return "admin-add-product-price";
	}
	
	@PostMapping("/admin/productpricelist/save")
	public String savePriceList(@ModelAttribute ProductsDto productsDto, Model model) {
		productService.updateProductPrices(productsDto);
		return "redirect:/admin/productpricelist";
	}	
}
