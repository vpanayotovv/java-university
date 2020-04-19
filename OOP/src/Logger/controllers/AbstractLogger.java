package Logger.controllers;

import Logger.Interfaces.Appender;
import Logger.Interfaces.Logger;
import Logger.enums.ReportLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractLogger implements Logger {

    private List<Appender> appenders;

    public AbstractLogger(Appender ... appenders){
        this.appenders = new ArrayList<>(Arrays.asList(appenders));
    }

    @Override
    public void logInfo(String data, String message) {
        this.callAllAppenders(data, ReportLevel.INFO,message);
    }

    @Override
    public void logWarning(String data, String message) {
        this.callAllAppenders(data,ReportLevel.INFO,message);
    }

    @Override
    public void logError(String data, String message) {
        this.callAllAppenders(data,ReportLevel.ERROR,message);
    }

    @Override
    public void logCritical(String data, String message) {
        this.callAllAppenders(data,ReportLevel.CRITICAL,message);
    }

    @Override
    public void logFatal(String data, String message) {
        this.callAllAppenders(data,ReportLevel.FATAL,message);
    }

    private void callAllAppenders(String data, ReportLevel reportlevel, String message) {

        for (Appender appender : appenders) {
            if (appender.getReportLevel().ordinal() <= reportlevel.ordinal()) {
                appender.append(data, reportlevel, message);
            }
        }
    }

    @Override
    public void addAppender(Appender appender) {
        this.appenders.add(appender);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Logger info")
                .append(System.lineSeparator());

        sb.append(
                this.appenders.stream()
                .map(Appender::toString)
                .collect(Collectors.joining(System.lineSeparator()))
        );
        return sb.toString();
    }
}
