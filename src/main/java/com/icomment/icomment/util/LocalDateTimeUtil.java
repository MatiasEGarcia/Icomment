package com.icomment.icomment.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class LocalDateTimeUtil {
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private LocalDateTime now;
	
	public String getFormatCurrentMoment() {
		now = LocalDateTime.now();
		return dateTimeFormatter.format(now);
	}
	
	public LocalDateTime getLocalDateTimeFrom(String dateTime) { 
		return LocalDateTime.parse(dateTime, dateTimeFormatter);
	}
	
	public boolean compare(LocalDateTime dateTime, LocalDateTime dateTime2) {
		if(dateTime.compareTo(dateTime2) < 0) {  //if dateTima is < than dateTime2, dateTime is previous
			return false;
		}
		return true;
	}
}


