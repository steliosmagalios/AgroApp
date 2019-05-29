package gr.eagro.agroapp;


import java.time.LocalDate;

public class CalendarEntry implements java.io.Serializable ,  Comparable<CalendarEntry> {
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

	@Override
	public int compareTo(CalendarEntry o) {

		if(this.getYear()>o.getYear()){
			return 1;
		}else if (this.getYear()<o.getYear()){
			return -1;
		}else {
			if (this.getMonth() > o.getMonth()) {
				return 1;
			} else if (this.getMonth() < o.getMonth()) {
				return -1;
			} else {
				if (this.getDay() > o.getDay()) {
					return 1;
				} else if (this.getDay() < o.getDay()) {
					return -1;
				} else return 0;
			}
		}
	}

	public LocalDate getDateAsLocalDate(){
		return LocalDate.of(this.year, this.month, this.day);

	}

	public void setDateFromLocalDate(LocalDate date){
		this.day= date.getDayOfMonth();
		this.month= date.getMonthValue();
		this.year= date.getYear();

	}

}
