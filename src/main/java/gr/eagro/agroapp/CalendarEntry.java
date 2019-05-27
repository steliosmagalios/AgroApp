package gr.eagro.agroapp;

import java.io.Serializable;

public class CalendarEntry implements Serializable {
	private int day;
	private int month;
	private int year;
	private String displayText;
	
	public CalendarEntry(int day, int month, int year, String displayText) {
		this.day=day;
		this.month = month;
		this.year = year;
		this.displayText = displayText;
	}


	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDisplayText() {
		return displayText;
	}

	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

	@Override
	public String toString() {
		return this.getDisplayText();
	}
}
