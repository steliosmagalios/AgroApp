package gr.eagro.agroapp.model;

import java.util.ArrayList;
import java.io.Serializable;

public class Calendar  implements Serializable{
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
