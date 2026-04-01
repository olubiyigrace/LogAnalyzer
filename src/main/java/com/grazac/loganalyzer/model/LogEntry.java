package com.grazac.loganalyzer.model;

import java.time.LocalDate;

public class LogEntry {
    private final String level; // INFO or ERROR
    private final LocalDate date;
    private final String message;

    public LogEntry(String level, LocalDate date, String message) {
        this.level = level;
        this.date = date;
        this.message = message;
    }

    public String getLevel() {
        return level;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}