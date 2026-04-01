package com.grazac.loganalyzer;

import com.grazac.loganalyzer.service.LogAnalyzerService;
import com.grazac.loganalyzer.util.LogParser;
import com.grazac.loganalyzer.exporter.Summary;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LogAnalyzerApplication implements CommandLineRunner {

	private final LogAnalyzerService service;

	public LogAnalyzerApplication(LogAnalyzerService service) {
		this.service = service;
	}

	public static void main(String[] args) {
		SpringApplication.run(com.grazac.loganalyzer.LogAnalyzerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// 1️⃣ Path to your log file
		String logFilePath = "/Users/macbook/Downloads/LogAnalyzer/log/app.log";

		// 2️⃣ Parse file and add logs to service
		service.addLogs(LogParser.parseFile(logFilePath));

		// 3️⃣ Print summary to console
		System.out.println("=== Log Summary ===");
		System.out.println("Total logs: " + service.countTotalLogs());
		System.out.println("INFO logs: " + service.countLevelLogs("INFO"));
		System.out.println("ERROR logs: " + service.countLevelLogs("ERROR"));
		System.out.println("WARNING logs: " + service.countLevelLogs("WARNING"));
		System.out.println("\nLogs grouped by date:");
		service.groupLogsByDate().forEach((date, logs) -> {
			System.out.println("Date: " + date);
			logs.forEach(log -> System.out.println("  [" + log.getLevel() + "] " + log.getMessage()));
		});

		// 4️⃣ Export summary to a file
		String summaryFilePath = "/Users/macbook/Downloads/LogAnalyzer/log/log-summary.txt";
		Summary.exportSummary(summaryFilePath, service);
		System.out.println("\nSummary exported to: " + summaryFilePath);
	}
}