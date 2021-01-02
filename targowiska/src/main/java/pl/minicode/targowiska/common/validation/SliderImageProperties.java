package pl.minicode.targowiska.common.validation;

import pl.minicode.targowiska.common.ImageProperties;

public class SliderImageProperties implements ImageProperties {

	public static SliderImageProperties createProperties() {
		return new SliderImageProperties();
	}

	@Override
	public int getMinimumHeight() {
		return 768;
	}
	
	@Override
	public int getMinimumWidth() {
		return 1366;
	}
}
