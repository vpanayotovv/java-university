package Logger.Interfaces;

public interface Logger {

    void logInfo(String data, String message);
    void logWarning(String data, String message);
    void logError(String data, String message);
    void logCritical(String data, String message);
    void logFatal(String data, String message);

    void addAppender(Appender appender);
}
