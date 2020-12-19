package pl.minicode.targowiska.guicontent;

import java.util.List;

import pl.minicode.targowiska.common.ICommonService;

public interface IGuiContentService extends ICommonService<GuiContent> {

	List<GuiContent> findByContentTypeIn(List<String> contentTypes);
}
