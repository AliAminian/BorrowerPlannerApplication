package com.lendico.borrower.planner.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	//Assumptions
	public final static String DateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public final static int  DAY_IN_MONTH = 30;
	public final static int DAY_IN_YEAR = 360;
	
	
	public static Date nextMonth(Date currentMonth) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(currentMonth);

		int day = cal.get(Calendar.DAY_OF_MONTH);
		int dayInYear = cal.get(Calendar.DAY_OF_YEAR);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		month = (month+1 > 12)? 1 : month+1;
		year = ( (month * DateUtil.DAY_IN_MONTH + day) < dayInYear)? year+1: year;
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		
		return cal.getTime();
	}
	
}
