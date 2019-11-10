package pl.minicode.targowiska.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	// inject via application.properties
	@Value("${welcome.message}")
	private String message;



	@GetMapping("/")
	public String showUsersListForm(Model model) {
		
		return "welcome"; // view
	}

//	// /hello?name=kotlin
//	@GetMapping("/hello")
//	public String mainWithParam(@RequestParam(name = "name", required = false, defaultValue = "Simple user") String name,
//			Model model) {
//		model.addAttribute("users", userRepository.findAll());
//		model.addAttribute("message", name);
//
//		return "welcome"; // view
//	}
}
