package pl.minicode.targowiska.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import pl.minicode.targowiska.service.IProductService;

@Controller
public class ProductController {

	@Autowired
	IProductService productService;
}
