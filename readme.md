# Log Analyzer API
A Spring Boot-based backend application that processes raw log files and converts them into meaningful insights such as log counts, filtering, and grouped summaries.

## Features
1. Count total logs
2. Count logs by level:
   - INFO
   - ERROR
   - WARNING
3. Filter logs by date
4. Group logs by date (descending order)
5. Export summarized results to file
6. RESTful API endpoints 
7. API documentation with Postman

## Tech Stack
* Java
* Spring Boot
* Spring Web
* Java NIO (File Handling)
* BufferedReader (for reading log files)
* Postman (API documentation)

## Project Structure

```
loganalyzer/
├── README.md                  # Project documentation
├── log/                        # Log files at project root
│   ├── app.log                  # Sample application logs
│   └── log-summary.txt          # Exported summary
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── grazac/
│                   └── loganalyzer/
│                       ├── LogAnalyzerApplication.java  # Main Spring Boot class
│                       ├── controller/                 # API layer
│                       ├── service/                    # Business logic
│                       ├── model/                      # Data models (LogEntry)
│                       ├── util/                       # Helpers (LogParser)
│                       └── exporter/                   # Export logic (SummaryExporter)
```



## How It Works
* The application expects logs in the following format: 
  -   [LEVEL]  yyyy-MM-dd  Message 
  - Example:
    - [INFO] User registered
    - [ERROR] Payment failed
    - [WARNING] High memory usage
* The application reads a log file using BufferedReader. Each line is parsed using a regex pattern.
* Data is converted into LogEntry objects
* Logs are processed to generate:
    - Counts 
    - Filters 
    - Grouped summaries

## API Endpoints
1. Summary (Grouped)
GET /api/logs/summary
Returns:
- Total logs
- INFO count
- ERROR count
- WARNING count
2. Filter Logs by Date
GET /api/logs/{date}

## API Documentation
The API is fully documented using Postman. 
* 🔗 View Documentation: https://documenter.getpostman.com/view/52630284/2sBXinHW3y

## Key Concepts Used
* Regex pattern matching
* File streaming with BufferedReader
* Optional handling
* Aggregation and grouping
* REST API design



