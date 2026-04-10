package com.grazac.loganalyzer;

import com.grazac.loganalyzer.service.LogAnalyzerService;
import com.grazac.loganalyzer.summary.SummaryExporter;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class LogAnalyzerApplication implements CommandLineRunner {
	private final LogAnalyzerService service;

	static void main(String[] args) {
		SpringApplication.run(com.grazac.loganalyzer.LogAnalyzerApplication.class, args);
	}

	@Override
	public void run(String @NonNull ... args) throws Exception {


		System.out.println("\n========================= LOG SUMMARY =========================");
		System.out.println("Total logs: " + service.countTotalLogs());
		System.out.println("INFO logs: " + service.countLevelLogs("INFO"));
		System.out.println("ERROR logs: " + service.countLevelLogs("ERROR"));
		System.out.println("WARNING logs: " + service.countLevelLogs("WARNING"));
		System.out.println("\n=================== LOGS GROUPED BY DATE =======================");
		service.groupLogsByDateDesc().forEach((date, logs) -> {
			System.out.println("Date: " + date);
			logs.forEach(log -> System.out.println("  [" + log.getLevel() + "] " + log.getMessage()));
		});

		String summaryFilePath = "/Users/macbook/Downloads/LogAnalyzer/log/log-summary.txt";
		SummaryExporter.exportSummary(summaryFilePath, service);
		System.out.println("\nSummary exported to: " + summaryFilePath);
	}
}