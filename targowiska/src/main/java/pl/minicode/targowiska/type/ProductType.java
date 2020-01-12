package pl.minicode.targowiska.type;

import java.util.Arrays;
import java.util.List;

public enum ProductType {

	VEGETABLE, FRUIT, OTHER;
	
	public static List<ProductType> getGuiValues(){
		return Arrays.asList(VEGETABLE, FRUIT, OTHER);
	}

	public String toDisplay() {
		String result = "";
		switch (this) {
		case VEGETABLE:
			result = "Warzywa";
			break;
		case FRUIT:
			result = "Owoce";
			break;
		case OTHER:
			result = "Inne";
			break;
		default:
			break;
		}
		return result;
	}
}