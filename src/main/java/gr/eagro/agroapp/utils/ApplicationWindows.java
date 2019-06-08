package gr.eagro.agroapp.utils;

public enum ApplicationWindows {

    MAIN_MENU_WINDOW         ("/scenes/MainMenuWindowView.fxml"),
    FERTILIZER_WINDOW        ("/scenes/FertilizerWindowView.fxml"),
    FERTILIZER_RESULT_WINDOW ("/scenes/FertilizerResultWindowView.fxml"),
    DISEASES_WINDOW          ("/scenes/DiseasesWindowView.fxml"),
    DISEASES_RESULT_WINDOW   ("/scenes/DiseasesResultWindowView.fxml"),
    HYBRIDS_WINDOW           ("/scenes/HybridsWindowView.fxml"),
    HYBRIDS_RESULT_WINDOW    ("/scenes/HybridsResultWindowView.fxml"),
    CALENDAR_WINDOW          ("/scenes/CalendarWindowView.fxml"),
    STATISTICS_ADD_WINDOW    ("/scenes/StatisticsAddWindowView.fxml"),
    STATISTICS_GRAPH_WINDOW  ("/scenes/StatisticsGraphWindowView.fxml"),
    ENTRY_CREATION_WINDOW    ("/scenes/EntryCreationWindowView.fxml");

    private String location;

    ApplicationWindows(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
