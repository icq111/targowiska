package pl.minicode.targowiska.common.validation;

import org.springframework.stereotype.Component;

import pl.minicode.targowiska.common.ImageProperties;

@Component
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
