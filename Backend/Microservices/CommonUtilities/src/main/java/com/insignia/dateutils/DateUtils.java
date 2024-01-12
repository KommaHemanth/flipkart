package com.insignia.dateutils;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateUtils {
	
    public static SimpleDateFormat createUtcDateFormat() {
    	
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
        
    }
}
