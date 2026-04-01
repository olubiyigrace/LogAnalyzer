package com.grazac.loganalyzer.controller;

import com.grazac.loganalyzer.model.LogEntry;
import com.grazac.loganalyzer.service.LogAnalyzerService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/logs")
public class LogAnalyzerController {

    private final LogAnalyzerService service;

    public LogAnalyzerController(LogAnalyzerService service) {
        this.service = service;
    }

    @GetMapping("/summary")
    public Map<String, Long> getSummary() {
        return Map.of(
                "total", service.countTotalLogs(),
                "INFO", service.countLevelLogs("INFO"),
                "ERROR", service.countLevelLogs("ERROR"),
                "WARNING", service.countLevelLogs("WARNING")
        );
    }

    @GetMapping("/by-date/{date}")
    public List<LogEntry> getLogsByDate(@PathVariable String date) {
        return service.filterByDate(LocalDate.parse(date));
    }
}