package pl.minicode.targowiska.productcategory;

import java.util.Arrays;
import java.util.List;

public enum ProductCategory {

	LOCAL_FRUIT, LOCAL_VEGETABLE, FOREIGN_FRUIT, FOREIGN_VEGETABLE;

	public static List<ProductCategory> getGuiValues() {
		return Arrays.asList(LOCAL_FRUIT, LOCAL_VEGETABLE, FOREIGN_FRUIT, FOREIGN_VEGETABLE);
	}

	public String toDisplay() {
		String result = "";
		switch (this) {
		case LOCAL_FRUIT:
			result = "Owoce krajowe";
			break;
		case FOREIGN_FRUIT:
			result = "Owoce zagraniczne";
			break;
		case LOCAL_VEGETABLE:
			result = "Warzywa krajowe";
			break;
		case FOREIGN_VEGETABLE:
			result = "Warzywa zagraniczne";
			break;
		default:
			break;
		}
		return result;
	}

	public boolean isLocalFruit() {
		return this == LOCAL_FRUIT;
	}

	public boolean isForeignFruit() {
		return this == FOREIGN_FRUIT;
	}

	public boolean isLocalVegetable() {
		return this == LOCAL_VEGETABLE;
	}

	public boolean isForeignVegetable() {
		return this == FOREIGN_VEGETABLE;
	}

}
