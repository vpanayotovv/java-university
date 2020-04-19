package Logger.controllers;

import Logger.Interfaces.Appender;
import Logger.Interfaces.Layout;
import Logger.enums.ReportLevel;

public abstract class AbstractAppender implements Appender {
    private Layout layout;
    private ReportLevel reportLevel;
    private int appendsCount;


    public AbstractAppender(ReportLevel reportLevel, Layout layout) {
        this.reportLevel = reportLevel;
        this.layout = layout;
    }

    public AbstractAppender(Layout layout)  {
        this(ReportLevel.INFO,layout);
    }

    protected Layout getLayout() {
        return this.layout;
    }

    public ReportLevel getReportLevel(){
        return this.reportLevel;
    }

    abstract String getType();


    @Override
    public String toString() {
        //Appender type: ConsoleAppender, Layout type: SimpleLayout, Report level: CRITICAL, Messages appended: 2
        StringBuilder sb  = new StringBuilder("Appender type: ");
        sb.append(this.getType())
                .append(" , Layout type: ")
                .append(this.layout.getType())
                .append(", ")
                .append(String.format("Report level: %s, ",this.getReportLevel().toString()))
                .append("Messages appended: ")
                .append(this.appendsCount);

        return sb.toString();
    }

    protected void incrementAppendsCount(){
        this.appendsCount++;
    }
}
