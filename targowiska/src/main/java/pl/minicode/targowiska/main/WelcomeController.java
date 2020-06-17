package pl.minicode.targowiska.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.minicode.targowiska.domain.News;
import pl.minicode.targowiska.domain.Product;
import pl.minicode.targowiska.service.INewsService;
import pl.minicode.targowiska.service.IProductService;

@Controller
public class WelcomeController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private INewsService newsService;
	
	@GetMapping("/")
	public String showUsersListForm(Model model) {
		//List<Product> list = productService.find3RandomProductPrices();
		Page<Product> productsList = productService.findAll(PageRequest.of(0, 4));
		Page<News> newsList = newsService.findAll(PageRequest.of(0, 4, Sort.by("insertStamp").descending()));
		
		model.addAttribute("productsList", productsList);
		model.addAttribute("newsList", newsList);
		return "welcome"; // view
	}
}
