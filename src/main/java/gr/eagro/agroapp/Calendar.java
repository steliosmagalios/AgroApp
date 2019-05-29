package gr.eagro.agroapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calendar  implements java.io.Serializable{
	private ArrayList<CalendarEntry> entries;



	public Calendar(){

		this.entries = new ArrayList<>();
	}

	public void addEntry(CalendarEntry entry) {
		entries.add(entry);
	}
	
	public void removeEntry(CalendarEntry entry) {
		entries.remove(entry);
	}
	

	public ArrayList<CalendarEntry> getEntries() {
		return entries;
	}


	
	
}
