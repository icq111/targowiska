package pl.minicode.targowiska.offer.external;

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

import pl.minicode.targowiska.common.PaginationUtils;
import pl.minicode.targowiska.offer.internal.Offer;
import pl.minicode.targowiska.offer.internal.OfferService;

@Controller
public class ExternalOfferController {

	@Autowired
	private OfferService offerService;
	
	@GetMapping("/admin/externalofferslist")
	public String showInternalOffersListPageForm(Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		int currentPage = page.orElse(PaginationUtils.DEFAULT_PAGE);
		int pageSize = size.orElse(PaginationUtils.PAGE_SIZE);

		Page<Offer> externalOffersList = offerService.findPaginatedExternalOffers(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("externalOffersList", externalOffersList);

		int totalPages = externalOffersList.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		return "admin-offers-external-list"; // view
	}
	
	@GetMapping("/admin/externalofferslist/newexternaloffer")
	public String showAddNewExternalOfferForm(Offer offer) {
		return "admin-add-external-offer";
	}

	@PostMapping("/admin/externalofferslist/addexternaloffer")
	public String addExternalOffer(@Valid Offer offer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "admin-add-external-offer";
		}

		offerService.saveExternalOffer(offer);
		return "redirect:/admin/externalofferslist";
	}
	
}
