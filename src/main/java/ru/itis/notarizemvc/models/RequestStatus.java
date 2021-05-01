package ru.itis.notarizemvc.models;

public enum RequestStatus {
    DRAFT("Черновик"),
    FREE("Свободен"),
    IN_PROGRESS("В процессе"),
    DONE("Подтверждён");

    private final String displayedValue;

    RequestStatus(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String getDisplayedValue() {
        return displayedValue;
    }
}
