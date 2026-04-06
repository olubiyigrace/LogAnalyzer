package com.grazac.loganalyzer.controller;

import com.grazac.loganalyzer.service.LogAnalyzerService;
import com.grazac.loganalyzer.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogAnalyzerController {
    private final LogAnalyzerService service;
    private final ResponseBuilder responseBuilder;

    @GetMapping("/summary")
    public ResponseEntity<?> getSummary() {
        var summary = Map.of(
                "Total Logs", service.countTotalLogs(),
                "INFO Logs", service.countLevelLogs("INFO"),
                "ERROR Logs", service.countLevelLogs("ERROR"),
                "WARNING Logs", service.countLevelLogs("WARNING")
        );
        return responseBuilder.buildResponse(true, "Log summary retrieved successfully!", HttpStatus.OK, summary);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<?> getLogsByDate(@PathVariable String date) {
        var requestedDate = service.filterByDate(LocalDate.parse(date));

        return responseBuilder.buildResponse(true, "Logs of " + date + " retrieved successfully", HttpStatus.OK, requestedDate);
    }
}