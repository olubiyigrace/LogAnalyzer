package com.grazac.loganalyzer.service;

import com.grazac.loganalyzer.model.LogEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LogAnalyzerService {

    private final List<LogEntry> logs = new ArrayList<>();

    public void addLogs(List<LogEntry> newLogs) {
        if (newLogs != null) logs.addAll(newLogs);
    }

    public long countTotalLogs() {
        return logs.size();
    }

    public long countLevelLogs(String level) {
        return logs.stream()
                .filter(log -> log.getLevel().equalsIgnoreCase(level))
                .count();
    }

    // Group logs by date in ascending order (original)
    public Map<LocalDate, List<LogEntry>> groupLogsByDate() {
        return logs.stream()
                .collect(Collectors.groupingBy(LogEntry::getDate));
    }

    // Group logs by date in descending order (new)
    public Map<LocalDate, List<LogEntry>> groupLogsByDateDesc() {
        Map<LocalDate, List<LogEntry>> grouped = logs.stream()
                .collect(Collectors.groupingBy(LogEntry::getDate));

        return grouped.entrySet().stream()
                .sorted((e1, e2) -> e2.getKey().compareTo(e1.getKey())) // descending
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public List<LogEntry> filterByDate(LocalDate date) {
        return logs.stream()
                .filter(log -> log.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public void clearLogs() {
        logs.clear();
    }
}