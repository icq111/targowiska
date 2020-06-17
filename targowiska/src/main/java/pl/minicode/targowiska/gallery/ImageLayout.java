package pl.minicode.targowiska.type;

public enum ImageLayout {

	// pionowy/poziomy
	VERTICAL, HORIZONTAL;

	public boolean isVertical() {
		return this.equals(VERTICAL);
	}

	public boolean isHorizontal() {
		return this.equals(HORIZONTAL);
	}
}
