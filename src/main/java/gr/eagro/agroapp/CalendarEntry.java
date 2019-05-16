package gr.eagro.agroapp;

public class CalendarEntry {
	private int day;
	private int week;
	private int month;
	private int year;
	private String displayText;
	
	public CalendarEntry(int day, int week, int month, int year, String displayText) {
		this.day= day;
		this.week = week;
		this.month = month;
		this.year = year;
		this.displayText = displayText;
	}
	

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
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
		return this.displayText;
	}
}
