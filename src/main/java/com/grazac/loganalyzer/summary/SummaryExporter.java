package com.grazac.loganalyzer.summary;

import com.grazac.loganalyzer.model.LogEntry;
import com.grazac.loganalyzer.service.LogAnalyzerService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SummaryExporter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void exportSummary(String filePath, LogAnalyzerService service) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("=== Log Summary ===\n");
            writer.write("Total logs: " + service.countTotalLogs() + "\n");
            writer.write("INFO logs: " + service.countLevelLogs("INFO") + "\n");
            writer.write("ERROR logs: " + service.countLevelLogs("ERROR") + "\n");
            writer.write("WARNING logs: " + service.countLevelLogs("WARNING") + "\n\n");

            writer.write("=== Logs Grouped by Date ===\n");
            Map<java.time.LocalDate, java.util.List<LogEntry>> grouped = service.groupLogsByDateDesc();
            for (var entry : grouped.entrySet()) {
                writer.write("Date: " + entry.getKey().format(DATE_FORMATTER) + "\n");
                for (LogEntry log : entry.getValue()) {
                    writer.write("  [" + log.getLevel() + "] " + log.getMessage() + "\n");
                }
                writer.write("\n");
            }
        }
    }
}