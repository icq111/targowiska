package pl.minicode.targowiska.productprice;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pl.minicode.targowiska.common.Status;
import pl.minicode.targowiska.common.date.DateUtils;
import pl.minicode.targowiska.common.date.StringDateValidator;
import pl.minicode.targowiska.product.ProductValidatorService;

@Controller
public class ProductPriceController {

	@Autowired
	private ProductPriceListViewService productPriceListViewService;

	@Autowired
	private ProductValidatorService productValidatorService;

	@Autowired
	private ProductPriceListService productPriceListService;

	@GetMapping("/cennik-produkty")
	public String showProductPriceListPageForm(Model model, @RequestParam("date") Optional<String> date) {
		List<ProductPriceListView> lastPriceList = Collections.emptyList();

		if(date.isPresent()) {
			StringDateValidator stringDateValidator = new StringDateValidator(date.get());
			boolean isValid = stringDateValidator.isValidUrlParamDateFormat();
			if(isValid) {
				Date formDate = DateUtils.formatToURLParamDate(date.get());
				lastPriceList = productPriceListViewService.getByDate(formDate);			
			}			
		} else {
			List<String> statuses = Arrays.asList(Status.ACTIVE.toString(), Status.INACTIVE.toString(), Status.DELETED.toString());
			lastPriceList = productPriceListViewService.findLastPriceListWhereStatusIn(statuses);			
		}

		ProductPriceListViewDto productPriceListViewDto = ProductPriceListViewDto
				.createProductPriceListViewDto(lastPriceList, false);

		model.addAttribute("productPriceListViewDto", productPriceListViewDto);

		return "cennik-produkty"; // view
	}
	
	
	@GetMapping("/admin/productpricelist")
	public String showProductListPageForm(Model model) {

		boolean isAddProductPricesAllowed = productValidatorService.isAddProductPricesAllowed();

		List<ProductPriceListView> lastPriceList = productPriceListViewService
				.findLastPriceListWhereStatusIn(Arrays.asList(Status.ACTIVE.toString(), Status.INACTIVE.toString()));
		ProductPriceListViewDto productPriceListViewDto = ProductPriceListViewDto
				.createProductPriceListViewDto(lastPriceList, isAddProductPricesAllowed);

		model.addAttribute("productPriceListViewDto", productPriceListViewDto);

		return "admin-product-price-list"; // view
	}

	@GetMapping("/admin/productpricelist/create")
	public String showCreateForm(Model model) {
		List<ProductPriceListView> lastPriceList = productPriceListViewService
				.findLastPriceListWhereStatusIn(Arrays.asList(Status.ACTIVE.toString(), Status.INACTIVE.toString()));
		ProductPriceListViewDto productPriceListViewDto = ProductPriceListViewDto
				.createProductPriceListViewDto(lastPriceList, true);
		ProductPriceListCreationDto creationList = new ProductPriceListCreationDto();

		for (ProductPriceListView item : productPriceListViewDto.getAll()) {
			creationList.addPriceListItem(item);
		}

		model.addAttribute("productPriceListViewDto", creationList);
		return "admin-add-product-price";
	}

	@PostMapping("/admin/productpricelist/save")
	public String savePriceList(@ModelAttribute ProductPriceListCreationDto productsDto, Model model) {
		productPriceListService.savePriceList(productsDto);
		return "redirect:/admin/productpricelist";
	}
}
