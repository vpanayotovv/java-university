package Logger.Interfaces;

import Logger.enums.ReportLevel;

public interface File {
    void write(String text);
    int getSize();
}
