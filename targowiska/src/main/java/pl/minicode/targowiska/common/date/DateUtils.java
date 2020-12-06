package pl.minicode.targowiska.common.date;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	private final static String[] monthShortcuts = new String[]{"Sty","Lut","Mar","Kwi","Maj","Cze","Lip","Sie","Wrz","Paz","Lis","Gru"};
	
	private DateUtils() {
	}
	
	public static String getMonthShortcut(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return monthShortcuts[calendar.get(Calendar.MONTH)];
	}

	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	} 
}
