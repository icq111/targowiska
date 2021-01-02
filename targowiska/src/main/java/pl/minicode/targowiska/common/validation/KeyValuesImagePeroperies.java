package pl.minicode.targowiska.common.validation;

import pl.minicode.targowiska.common.ImageProperties;

public class KeyValuesImagePeroperies implements ImageProperties {
	
	private int width = 1366;
	private int height = 768;
	private int sizeInKb = 350000;

	public static KeyValuesImagePeroperies createProperties() {
		return new KeyValuesImagePeroperies();
	}

	@Override
	public int getMinimumWidth() {
		return this.width;
	}
	
	@Override
	public int getMinimumHeight() {
		return this.height;
	}
	
	@Override
	public int getSizeInKB() {
		return this.sizeInKb;
	}
}
