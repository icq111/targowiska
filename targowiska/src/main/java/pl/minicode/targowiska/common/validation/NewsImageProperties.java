package pl.minicode.targowiska.common.validation;

import pl.minicode.targowiska.common.ImageProperties;

public class NewsImageProperties implements ImageProperties {

	public static NewsImageProperties createProperties() {
		return new NewsImageProperties();
	}
	
	@Override
	public int getMinimumWidth() {
		return 480;
	}
	
	@Override
	public int getMinimumHeight() {
		return 480;
	}
}
