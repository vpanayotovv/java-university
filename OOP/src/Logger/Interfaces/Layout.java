package Logger.Interfaces;

import Logger.enums.ReportLevel;

public interface Layout {
    String format(String date, ReportLevel reportLevel, String message);

    String getType();

}
