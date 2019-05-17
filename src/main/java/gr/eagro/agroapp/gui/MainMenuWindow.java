package gr.eagro.agroapp.gui;

public class MainMenuWindow extends ApplicationWindow {

    public MainMenuWindow() {
        super("Αρχική", EnumWindowLocation.MAIN_MENU_WINDOW);
    }

    public void openFertilizerWindow() {
        openWindow(EnumWindowLocation.FERTILIZER_WINDOW);
    }

    public void openDiseasesWindow() {
        openWindow(EnumWindowLocation.DISEASES_WINDOW);
    }

    public void openHybridsWindow() {
        openWindow(EnumWindowLocation.HYBRIDS_WINDOW);
    }

    public void openCalendarWindow() {
        openWindow(EnumWindowLocation.CALENDAR_WINDOW);
    }

    public void openStatisticsWindow() {
        openWindow(EnumWindowLocation.STATISTICS_ADD_WINDOW);
    }
}
