package Logger.controllers;

import Logger.Interfaces.Layout;
import Logger.enums.ReportLevel;

public class SimpleLayout implements Layout {
    @Override
    public String format(String date, ReportLevel reportLevel, String message) {
        return String.format("%s - %s - %s",date,reportLevel,message);
    }

    @Override
    public String getType() {
        return "SimpleLayout";
    }
}
