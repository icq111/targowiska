package pl.minicode.targowiska.common.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringDateValidator {

	
	private String dateStr;

	public StringDateValidator(String dateStr) {
		super();
		this.dateStr = dateStr;
	}

	public boolean isValidUrlParamDateFormat() {
		return isValid(DateConstans.URL_PARAM_DATE_FORMAT);
	}

	private boolean isValid(String dateFormat) {
		if(this.dateStr == null) {
			return false;
		}
		DateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		try {
			sdf.parse(this.dateStr);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}
}
