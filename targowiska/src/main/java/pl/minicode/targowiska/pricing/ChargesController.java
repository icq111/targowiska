package pl.minicode.targowiska.pricing;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.util.ListUtils;

import pl.minicode.targowiska.common.INotificationService;

@Controller
public class ChargesController {

	@Autowired
	private IChargesService chargesService;

	@Autowired
	private INotificationService notifyService;

	@GetMapping("/cennik-oplaty")
	public String showChargesList(Model model) {
		List<Charges> charges = chargesService.findActiveCharges();
		if (ListUtils.isEmpty(charges)) {
			notifyService.addInfoMessage("No any charges to show");
		}

		model.addAttribute("charges", charges);
		return "cennik-oplaty"; // view
	}

	@GetMapping("/admin/cennik-oplaty")
	public String showAdminChargesList(Model model) {

		List<Charges> charges = chargesService.findAll();
		if(ListUtils.isEmpty(charges)) {
			charges = Collections.emptyList();
		}
		model.addAttribute("charges", charges);
		if (ListUtils.isEmpty(charges)) {
			notifyService.addInfoMessage("No any charges to show");

		}
		return "admin-cennik-oplaty"; // view
	}

	@GetMapping("/admin/cennik-oplaty/nowy")
	public String showAddNewChargesForm(Charges charges) {
		return "admin-cennik-oplaty-nowy";
	}

	@PostMapping("/admin/cennik-oplat/zapisz")
	public String saveCharges(@Valid Charges charges, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-news";
		}
		chargesService.save(charges);
		return "redirect:/admin/cennik-oplaty";
	}
}
