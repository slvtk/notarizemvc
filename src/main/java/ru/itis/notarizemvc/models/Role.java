package ru.itis.notarizemvc.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_CLIENT("Клиент"),
    ROLE_NOTARY("Нотариус");

    private final String displayedValue;

    Role(String displayedValue) {
        this.displayedValue = displayedValue;
    }

    public String getDisplayedValue() {
        return displayedValue;
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
