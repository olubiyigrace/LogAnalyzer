package com.grazac.loganalyzer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor

public class LogEntry {
    private final String level;
    private final LocalDate date;
    private final String message;
}