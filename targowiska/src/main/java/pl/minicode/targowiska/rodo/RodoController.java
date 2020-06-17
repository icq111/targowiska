package pl.minicode.targowiska.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.minicode.targowiska.domain.Rodo;
import pl.minicode.targowiska.service.IRodoService;

@Controller
public class RodoController {

	@Autowired
	IRodoService rodoService;

	@GetMapping("/admin/rodo")
	public String showRodoForm(Model model) {
		List<Rodo> rodoList = rodoService.findAll();

		Rodo rodo = null;
		if(!rodoList.isEmpty()) {
			rodo = rodoList.get(0);
		}
		model.addAttribute("rodo", rodo);
		return "admin-rodo"; // view
	}

	@GetMapping("/admin/rodo/new")
	public String showAddRodoForm(Rodo rodo) {
		return "admin-add-rodo";
	}

	@PostMapping("/admin/rodo/save")
	public String saveRodo(@Valid Rodo rodo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-rodo";
		}

		rodoService.save(rodo);
		return "redirect:/admin/rodo";
	}
}
