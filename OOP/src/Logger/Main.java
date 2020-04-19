package Logger;

import Logger.Interfaces.Appender;
import Logger.Interfaces.AppenderFactory;
import Logger.Interfaces.LayoutFactory;
import Logger.Interfaces.Logger;
import Logger.controllers.AppenderWorkshop;
import Logger.controllers.LayoutWorkshop;
import Logger.controllers.MessageLogger;
import Logger.enums.ReportLevel;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Layout simpleLayout = new SimpleLayout();
//        Appender consoleAppender = new ConsoleAppender(simpleLayout);
//        Logger logger = new MessageLogger(consoleAppender);
//
//        logger.logError("3/26/2015 2:08:11 PM", "Error parsing JSON.");
//        logger.logInfo("3/26/2015 2:08:11 PM", "User Pesho successfully registered.");


        AppenderFactory appenderFactory = new AppenderWorkshop();
        LayoutFactory layoutFactory = new LayoutWorkshop();

        Logger logger = new MessageLogger();
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] splitLine = scanner.nextLine().split("\\s+");
            ReportLevel reportLevel;
            if (splitLine.length == 3) {
                reportLevel = ReportLevel.valueOf(splitLine[2].toUpperCase());
            } else {
                reportLevel = ReportLevel.INFO;
            }
            Appender appender = appenderFactory.produce(splitLine[0], reportLevel, layoutFactory.produce(splitLine[1]));
            logger.addAppender(appender);
        }
        String input = scanner.nextLine();

        while (!input.equals("END")) {
            String[] tokens = input.split("\\|");
            ReportLevel reportLevel = ReportLevel.valueOf(tokens[0]);
            String data = tokens[1];
            String message = tokens[2];

            switch (reportLevel) {
                case INFO:
                    logger.logInfo(data, message);
                    break;
                case WARNING:
                    logger.logWarning(data, message);
                    break;
                case ERROR:
                    logger.logError(data, message);
                    break;
                case CRITICAL:
                    logger.logCritical(data, message);
                    break;
                case FATAL:
                    logger.logFatal(data, message);
                    break;
                default:
                    throw new IllegalStateException("Unknown enum value for " + reportLevel);
            }


            input = scanner.nextLine();
        }

        System.out.println(logger.toString());


    }

}
