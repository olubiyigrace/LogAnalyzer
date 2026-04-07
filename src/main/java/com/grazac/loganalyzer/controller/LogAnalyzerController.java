package com.grazac.loganalyzer.controller;

import com.grazac.loganalyzer.service.LogAnalyzerService;
import com.grazac.loganalyzer.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogAnalyzerController {
    private final LogAnalyzerService logAnalyzerService;
    private final ResponseBuilder responseBuilder;

    @GetMapping("/all")
    public ResponseEntity<?> getAllLogs() {
        var parsedLogs = logAnalyzerService.getLogs();
        return responseBuilder.buildResponse(true, "Logs retrieved successfully!", HttpStatus.OK, parsedLogs);

    }
    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
            var summary = new HashMap<>();
            for (String level : List.of("INFO", "ERROR", "WARNING")) {
                summary.put(level, logAnalyzerService.countLevelLogs(level));
            }
                summary.put("TOTAL", logAnalyzerService.countTotalLogs());
        return responseBuilder.buildResponse(true, "Log summary retrieved successfully!", HttpStatus.OK, summary);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<?> getLogsByDate(@PathVariable String date) {
        var requestedDate = logAnalyzerService.filterByDate(LocalDate.parse(date));

        return responseBuilder.buildResponse(true, "Logs of " + date + " retrieved successfully", HttpStatus.OK, requestedDate);
    }


}