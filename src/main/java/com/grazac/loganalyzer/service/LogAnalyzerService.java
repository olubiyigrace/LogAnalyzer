package com.grazac.loganalyzer.service;

import com.grazac.loganalyzer.model.LogEntry;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class LogAnalyzerService {

    private final List<LogEntry> logs = new ArrayList<>(2_000_000);

    public void addLogs(List<LogEntry> newLogs) {
        if (newLogs != null)
            logs.addAll(newLogs);
    }

    public long countTotalLogs() {
        return logs.size();
    }

    public long countLevelLogs(String level) {
        return logs.stream()
                .filter(log -> log.getLevel().equalsIgnoreCase(level))
                .count();
    }

    public Map<LocalDate, List<LogEntry>> groupLogsByDateDesc() {
        Map<LocalDate, List<LogEntry>> groupedLogs = logs.stream()
                .collect(Collectors.groupingBy(LogEntry::getDate));

        return groupedLogs.entrySet().stream()
                .sorted((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (entry1, entry2) -> entry1, LinkedHashMap::new));
    }

    public List<LogEntry> filterByDate(LocalDate date) {
        return logs.stream()
                .filter(log -> log.getDate().equals(date))
                .collect(Collectors.toList());
    }
}