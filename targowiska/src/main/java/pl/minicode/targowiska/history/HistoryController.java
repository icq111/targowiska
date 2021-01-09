package pl.minicode.targowiska.history;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

	@GetMapping("/historia")
	public String showForm(Model model) {
		return "historia";
	}
}
