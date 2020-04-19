package Logger.controllers;

import Logger.Interfaces.Appender;
import Logger.Interfaces.Layout;
import Logger.enums.ReportLevel;

public class ConsoleAppender extends AbstractAppender {

    public ConsoleAppender(ReportLevel reportLevel, Layout layout) {
        super(reportLevel, layout);
    }

    @Override
    String getType() {
        return "ConsoleAppender";
    }

    @Override
    public void append(String date, ReportLevel reportLevel,String message) {
        System.out.println(this.getLayout().format(date,reportLevel,message));
        this.incrementAppendsCount();
    }
}
