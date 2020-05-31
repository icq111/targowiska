package pl.minicode.targowiska.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.minicode.targowiska.domain.Offer;
import pl.minicode.targowiska.service.impl.OfferService;
import pl.minicode.targowiska.utils.PaginationUtils;

@Controller
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	@GetMapping("/admin/internalofferslist")
	public String showInternalOffersListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<Offer> internalOffersList = offerService.findPaginatedInternalOffers(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("internalOffersList", internalOffersList);

		int totalPages = internalOffersList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "admin-offers-internal-list"; // view
	}
	
	@GetMapping("/admin/internalofferslist/newinternaloffer")
	public String showAddNewInternalOfferForm(Offer offer) {
		return "admin-add-internal-offer";
	}

	@PostMapping("/admin/internalofferslist/addinternaloffer")
	public String addInternalOffer(@Valid Offer offer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-internal-offer";
		}

		offerService.saveInternalOffer(offer);
		return "redirect:/admin/internalofferslist";
	}
	
}
