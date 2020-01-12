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

import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.service.IProductService;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class ProductController {

	@Autowired
	private IProductService productService;

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
	public String addProduct(@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-product";
		}

		productService.save(product);
		return "redirect:/admin/productlist";
	}
}
