package pl.minicode.targowiska.regulations;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegulationsController {

	@GetMapping("/regulamin")
	public String showForm(Model model) {
		return "regulamin";
	}
}
