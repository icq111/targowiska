package pl.minicode.targowiska.offer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferController {

	@GetMapping("/oferta")
	public String showForm(Model model) {
		return "oferta";
	}
}
