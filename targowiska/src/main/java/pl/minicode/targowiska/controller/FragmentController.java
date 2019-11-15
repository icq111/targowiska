package pl.minicode.targowiska.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FragmentController {

	@GetMapping("fragmenttest")
	public String showFragmentForm(Model model) {
		
		return "fragment"; // view
	}
	
	@GetMapping("fragment-items")
	public String showFragmentItemsForm(Model model) {
		
		return "fragment_repository"; // view
	}
}
