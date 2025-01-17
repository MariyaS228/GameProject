package Masha.Logs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private final DateTimeFormatter DATE_TIME_FORMATTER;
    private String format = "dd-MM-yyyy HH.mm.ss";
    private final String logDirectory;

    public Logger(String format) {
        this.format = format;
        this.DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(this.format);
        this.logDirectory = initializeRelativeLogPath("src/Masha/Logs/Files");
        createLogDirectory();
    }

    public Logger() {
        this.DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(format);
        this.logDirectory = initializeRelativeLogPath("src/Masha/Logs/Files");
        createLogDirectory();
    }

    private void createLogDirectory() {
        File directory = new File(logDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public void log(String message) {
        writeToLogFile(getTimestamp() + " [LOG] " + message);
    }

    public void info(String message) {
        writeToLogFile(getTimestamp() + " [INFO] " + message);
    }

    public void error(String message) {
        writeToLogFile(getTimestamp() + " [ERROR] " + message);
    }

    private void writeToLogFile(String logMessage) {
        String dateLogFileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format)) + ".log";
        File dateLogFile = new File(logDirectory, dateLogFileName);
        File latestLogFile = new File(logDirectory, "latest_log.log");

        try (BufferedWriter dateLogWriter = new BufferedWriter(new FileWriter(dateLogFile, true));
             BufferedWriter latestLogWriter = new BufferedWriter(new FileWriter(latestLogFile))) {

            // Write to the date-specific log file
            dateLogWriter.write(logMessage);
            dateLogWriter.newLine();

            // Write to the latest log file
            latestLogWriter.write(logMessage);
            latestLogWriter.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String initializeRelativeLogPath(String relativePath) {
        Path projectBasePath = Paths.get("").toAbsolutePath();
        Path logPath = projectBasePath.resolve(relativePath);
        return logPath.toString();
    }

    private String getTimestamp() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
}
