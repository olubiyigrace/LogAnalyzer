package com.grazac.loganalyzer.util;

import com.grazac.loganalyzer.model.LogEntry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

    private static final Pattern LOG_PATTERN =
            Pattern.compile("\\[(INFO|ERROR|WARNING)]\\s+(\\d{4}-\\d{2}-\\d{2})\\s+(.*)");
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Parse a single line into a LogEntry
    public static Optional<LogEntry> parseLine(String line) {
        Matcher matcher = LOG_PATTERN.matcher(line);
        if (matcher.matches()) {
            String level = matcher.group(1);
            LocalDate date = LocalDate.parse(matcher.group(2), FORMATTER);
            String message = matcher.group(3);
            return Optional.of(new LogEntry(level, date, message));
        }
        return Optional.empty(); // malformed line
    }

    // Read file and return list of LogEntry objects
    public static List<LogEntry> parseFile(String filePath) throws IOException {
        List<LogEntry> parsedLogs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseLine(line).ifPresent(parsedLogs::add);
            }
        }
        return parsedLogs;
    }
}