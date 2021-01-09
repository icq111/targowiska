package pl.minicode.targowiska.rodo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import pl.minicode.targowiska.textpicture.ITextPictureService;
import pl.minicode.targowiska.textpicture.TextPicture;

@Controller
public class RodoController {

	@Autowired
	ITextPictureService rodoService;

	@GetMapping("/admin/rodo")
	public String showRodoForm(Model model) {
		List<TextPicture> rodoList = rodoService.findByOwnerControllerClass(this.getClass().getSimpleName());

		TextPicture textPicture = null;
		if(!rodoList.isEmpty()) {
			textPicture = rodoList.get(0);
		}
		model.addAttribute("textPicture", textPicture);
		return "admin-rodo"; // view
	}

	@GetMapping("/admin/rodo/new")
	public String showAddRodoForm(TextPicture rodo) {
		return "admin-add-rodo";
	}
	
	@GetMapping("/admin/rodo/edit/{id}")
	public String showUpdateFormfffffff(@PathVariable("id") long id, Model model) {
		TextPicture textPicture = rodoService.findById(id);
	    
	    model.addAttribute("textPicture", textPicture);
	    return "admin-add-rodo";
	}

	@PostMapping("/admin/rodo/save")
	public String saveRodo(@Valid TextPicture rodo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-rodo";
		}
		rodo.setOwnerControllerClass(this.getClass().getSimpleName());
		rodoService.save(rodo);
		return "redirect:/admin/rodo";
	}
	
	@PostMapping("/admin/rodo/save/{id}")
	public String saveEditedRodo(@PathVariable("id")  Optional<Long> id, @Valid TextPicture rodo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-rodo";
		}
		rodo.setId(id.get());
		rodo.setOwnerControllerClass(this.getClass().getSimpleName());
		rodoService.save(rodo);
		return "redirect:/admin/rodo";
	}
}
