package ru.itis.notarizemvc.models;

public enum FileStatus {
    NOTARIZED("Заверен"),
    NOT_NOTARIZED("Не заверен");

    private final String displayedValue;

    FileStatus(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String getDisplayedValue() {
        return displayedValue;
    }
}
