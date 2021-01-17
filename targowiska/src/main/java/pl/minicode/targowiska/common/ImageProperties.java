package pl.minicode.targowiska.common;

public interface ImageProperties {

	public default int getSizeInBytes() {
		return 200000;
	}

	public default int getMinimumWidth() {
		return 640;
	}

	public default int getMinimumHeight() {
		return 480;
	}
	
	
}
