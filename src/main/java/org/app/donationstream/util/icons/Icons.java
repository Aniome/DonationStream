package org.app.donationstream.util.icons;

public enum Icons {
    DATA_REPORTS_ICON("ci-chart-line-data"),
    SETTINGS_ICON("ci-audio-console");

    private final String icon;

    Icons(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
