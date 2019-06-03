package gr.eagro.agroapp;


import java.time.LocalDate;
import java.io.Serializable;

public class CalendarEntry implements Serializable ,  Comparable<CalendarEntry> {
	private int day;
	private int month;
	private int year;
	private String displayText;

    /**
     *
     * @param day
     * @param month
     * @param year
     * @param displayText
     */
	public CalendarEntry(int day, int month, int year, String displayText) {
		this.day=day;
		this.month = month;
		this.year = year;
		this.displayText = displayText;
	}

    /**
     *
     * @return
     */
	public int getDay() {
		return day;
	}

    /**
     *
     * @param day
     */
	public void setDay(int day) {
		this.day = day;
	}

    /**
     *
     * @return
     */
	public int getMonth() {
		return month;
	}

    /**
     *
     * @param month
     */
	public void setMonth(int month) {
		this.month = month;
	}

    /**
     *
     * @return
     */
	public int getYear() {
		return year;
	}

    /**
     *
     * @param year
     */
	public void setYear(int year) {
		this.year = year;
	}

    /**
     *
     * @return
     */
	public String getDisplayText() {
		return displayText;
	}

    /**
     *
     * @param displayText
     */
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}

    /**
     *
     * @return
     */
	@Override
	public String toString() {
		return this.getDisplayText();
	}

    /**
     *
     * @param o
     * @return
     */
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

    /**
     *
     * @return
     */
	public LocalDate getDateAsLocalDate(){
		return LocalDate.of(this.year, this.month, this.day);

	}

    /**
     *
     * @param date
     */
	public void setDateFromLocalDate(LocalDate date){
		this.day= date.getDayOfMonth();
		this.month= date.getMonthValue();
		this.year= date.getYear();

	}

}
