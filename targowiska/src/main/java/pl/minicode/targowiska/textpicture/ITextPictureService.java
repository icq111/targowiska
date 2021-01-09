package pl.minicode.targowiska.textpicture;

import java.util.List;

import pl.minicode.targowiska.common.ICommonService;

public interface ITextPictureService extends ICommonService<TextPicture> {

	List<TextPicture> findByOwnerControllerClass(String ownerControllerClass);
}
