package pl.minicode.targowiska.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pl.minicode.targowiska.news.INewsService;
import pl.minicode.targowiska.news.News;
import pl.minicode.targowiska.product.IProductService;
import pl.minicode.targowiska.product.Product;

@Controller
public class WelcomeController {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private INewsService newsService;
	
	@GetMapping("/")
	public String showWelcomeSite(Model model) {
		//List<Product> list = productService.find3RandomProductPrices();
		Page<Product> productsList = productService.findAll(PageRequest.of(0, 4));
		Page<News> newsList = newsService.findAll(PageRequest.of(0, 4, Sort.by("insertStamp").descending()));
		
		model.addAttribute("productsList", productsList);
		model.addAttribute("newsList", newsList);
		return "welcome2"; // view
	}
}
