package pl.minicode.targowiska.type;

import java.util.Arrays;
import java.util.List;

public enum Status {

	ACTIVE,
	INACTIVE,
	DELETED;
	
	public static List<Status> getGuiValues(){
		return Arrays.asList(ACTIVE, INACTIVE);
	}
	
	public String toDisplay() {
		String result = "";
		switch (this) {
		case ACTIVE:
			result = "Aktywny";
			break;
		case INACTIVE:
			result = "Nieaktywny";
			break;
		default:
			break;
		}
		return result;
	}
}

