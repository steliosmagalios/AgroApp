package gr.eagro.agroapp;

import java.util.ArrayList;

public class Calendar  implements java.io.Serializable{
	private ArrayList<CalendarEntry> entries;

	public Calendar(ArrayList<CalendarEntry> entries) {
		this.entries = entries;
	}

	public Calendar(){}

	public void addEntry(CalendarEntry entry) {
		entries.add(entry);
	}
	
	public void removeEntry(CalendarEntry entry) {
		entries.remove(entry);
	}
	

	public ArrayList<CalendarEntry> getEntries() {
		return entries;
	}

	public void setEntries(ArrayList<CalendarEntry> entries) {
		this.entries = entries;
	}
	
	
}
