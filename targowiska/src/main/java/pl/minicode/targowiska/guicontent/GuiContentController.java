package pl.minicode.targowiska.guicontent;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GuiContentController {
	
	@Autowired
	private IGuiContentService guiContentService;
	
	@Autowired
	private Environment applicationProperty;

	@GetMapping("/admin/gui-content/main-page-content")
	public String showMainPageContent(Model model) {
		
		String a = applicationProperty.getProperty("INDEX_HEAD_TEXT1.text.length");
		String b = applicationProperty.getProperty("INDEX_HEAD_SUBTEXT1.text.length");
		String c = applicationProperty.getProperty("INDEX_HEAD_TEXT2.text.length");

		System.out.println(a);
		
		List<String> categories = Arrays.asList(
				GuiResourceConstans.INDEX_1_SECTION_HEADER,
				GuiResourceConstans.INDEX_1_SECTION_TEXT,
				GuiResourceConstans.INDEX_HEAD_SUBTEXT1,
				GuiResourceConstans.INDEX_HEAD_SUBTEXT2,
				GuiResourceConstans.INDEX_HEAD_SUBTEXT3,
				GuiResourceConstans.INDEX_HEAD_SUBTEXT4,
				GuiResourceConstans.INDEX_HEAD_TEXT1,
				GuiResourceConstans.INDEX_HEAD_TEXT2,
				GuiResourceConstans.INDEX_HEAD_TEXT3,
				GuiResourceConstans.INDEX_HEAD_TEXT4
				);
		List<GuiContent> contents = guiContentService.findByContentTypeIn(categories);
		Map<String, GuiContent> typeContentMap = contents.stream().collect(Collectors.toMap(GuiContent::getContentType, guiContent -> guiContent));
			
		model.addAttribute("INDEX_1_SECTION_HEADER", typeContentMap.get("INDEX_1_SECTION_HEADER"));
		model.addAttribute("INDEX_1_SECTION_TEXT", typeContentMap.get("INDEX_1_SECTION_TEXT"));
		model.addAttribute("INDEX_HEAD_SUBTEXT1", typeContentMap.get("INDEX_HEAD_SUBTEXT1"));
		model.addAttribute("INDEX_HEAD_SUBTEXT2", typeContentMap.get("INDEX_HEAD_SUBTEXT2"));
		model.addAttribute("INDEX_HEAD_SUBTEXT3", typeContentMap.get("INDEX_HEAD_SUBTEXT3"));
		model.addAttribute("INDEX_HEAD_SUBTEXT4", typeContentMap.get("INDEX_HEAD_SUBTEXT4"));
		model.addAttribute("INDEX_HEAD_TEXT1", typeContentMap.get("INDEX_HEAD_TEXT1"));
		model.addAttribute("INDEX_HEAD_TEXT2", typeContentMap.get("INDEX_HEAD_TEXT2"));
		model.addAttribute("INDEX_HEAD_TEXT3", typeContentMap.get("INDEX_HEAD_TEXT3"));
		model.addAttribute("INDEX_HEAD_TEXT4", typeContentMap.get("INDEX_HEAD_TEXT4"));
		return "admin-main-page-content"; // view
	}
	
	@GetMapping("/admin/gui-content/offer-page-content")
	public String showOfferPageContent(Model model) {


			
		//model.addAttribute("pageNumbers", pageNumbers);
		
		return "admin-offer-page-content"; // view
	}
	
	@GetMapping("/admin/gui-content/history-page-content")
	public String showHistoryPageContent(Model model) {


			
		//model.addAttribute("pageNumbers", pageNumbers);
		
		return "admin-history-page-content"; // view
	}
	
	@GetMapping("/admin/gui-content/regulations-page-content")
	public String showRegulationsPageContent(Model model) {


			
		//model.addAttribute("pageNumbers", pageNumbers);
		
		return "admin-regulations-page-content"; // view
	}
}
