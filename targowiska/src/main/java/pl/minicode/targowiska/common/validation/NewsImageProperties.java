package pl.minicode.targowiska.common.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.minicode.targowiska.ConfigProperties;
import pl.minicode.targowiska.common.ImageProperties;

@Component
public class NewsImageProperties implements ImageProperties  {
	
	@Autowired
	private ConfigProperties applicationProperties;

	public int getMinimumWidth() {
		String prop = applicationProperties.getConfigValue("image.news.width");
		return prop != null && !prop.isEmpty() ? new Integer(prop) : ImageProperties.super.getMinimumWidth();
	}
	

	public int getMinimumHeight() {
		String prop =  applicationProperties.getConfigValue("image.news.height");
		return prop != null && !prop.isEmpty() ? new Integer(prop) : ImageProperties.super.getMinimumHeight();
	}
	

	public int getSizeInBytes() {
		String prop = applicationProperties.getConfigValue("image.news.size.kilobytes");
		return  prop != null && !prop.isEmpty() ? new Integer(prop) * 1000 : ImageProperties.super.getSizeInBytes();
	}
}
