package Logger.controllers;

import Logger.Interfaces.File;
import Logger.Interfaces.Layout;
import Logger.enums.ReportLevel;

public class FileAppender extends AbstractAppender {
    private File file;


    public FileAppender(ReportLevel reportLevel, Layout layout) {
        super(reportLevel, layout);
        this.file = new LogFile();
    }

    @Override
    String getType() {
        return "FileAppender";
    }

    @Override
    public void append(String date, ReportLevel reportLevel, String message) {
        String format = this.getLayout().format(date, reportLevel, message);
        this.file.write(format);
        super.incrementAppendsCount();
    }

    @Override
    public String toString() {
        return super.toString() + ", File size: " + this.file.getSize();
    }
}
