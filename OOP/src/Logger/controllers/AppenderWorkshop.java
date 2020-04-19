package Logger.controllers;

import Logger.Interfaces.Appender;
import Logger.Interfaces.AppenderFactory;
import Logger.Interfaces.Layout;
import Logger.enums.ReportLevel;

public class AppenderWorkshop implements AppenderFactory {
    @Override
    public Appender produce(String type, ReportLevel reportLevel, Layout layout) {
        new ConsoleAppender(reportLevel, layout);
        switch (type) {
            case "ConsoleAppender":
                return new ConsoleAppender(reportLevel,layout);
            case "FileAppender":
                return new FileAppender(reportLevel, layout);
            default:
                throw new IllegalStateException("Now valid type of appender for " + type + " param");

        }
    }
}
