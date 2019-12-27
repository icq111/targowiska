package pl.minicode.targowiska.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String showUsersListForm(Model model) {
		
		return "welcome"; // view
	}
}
