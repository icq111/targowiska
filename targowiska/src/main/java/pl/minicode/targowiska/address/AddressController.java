package pl.minicode.targowiska.address;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import pl.minicode.targowiska.common.validation.NIPValidator;

@Controller
public class AddressController {
	
	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private NIPValidator nipValidator;
	
	@GetMapping("/admin/address")
	public String showAddressList(Model model) {
		Address address = null;
		List<Address> list = addressService.findAll();
		if(list.size() > 0) {
			address = list.get(0);
		}
		model.addAttribute("address", address);
		return "admin-address-list"; // view
	}

	@GetMapping("/admin/address/create")
	public String showAddNewContractorForm(Address address, Model model) {
		List<Address> list = addressService.findAll();
		if(list.size() > 0) {
			address = list.get(0);
		}
		model.addAttribute("address", address);
		
		
		
		return "admin-add-address";
	}

	@PostMapping("/admin/address/save")
	public String addNewContractor(@Valid Address address, BindingResult result, Model model) {
		if( !address.getNip().isEmpty()) {
			nipValidator.validate(address, result);			
		}
		
		if (result.hasErrors()) {
			return "admin-add-address";
		}
		
		addressService.save(address);
		return "redirect:/admin/address";
	}

}
