package gr.eagro.agroapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Calendar  implements java.io.Serializable{
	private ArrayList<CalendarEntry> entries;



	public Calendar(){

		this.entries = new ArrayList<>();
	}

	/**
	 *
	 * @param entry
	 */
	public void addEntry(CalendarEntry entry) {
		entries.add(entry);
	}

	/**
	 *
	 * @param entry
	 */
	public void removeEntry(CalendarEntry entry) {
		entries.remove(entry);
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<CalendarEntry> getEntries() {
		return entries;
	}


	
	
}
