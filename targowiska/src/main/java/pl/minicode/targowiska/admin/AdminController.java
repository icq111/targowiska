package pl.minicode.targowiska.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

	@GetMapping("/admin/panel")
	public String showNewsListForm(Model model) {
		return "admin-panel"; // view
	}
}
