package console;

import domain.external.Exchange;
import domain.external.Repository.InMemoryCHP;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;
import external.CurrConvExchangeService;

import java.util.Scanner;

public class ConsoleRunner {

    public void run() {

        Scanner scanner = new Scanner(System.in);
        Exchange exchange = new CurrConvExchangeService();
        Logger logger = new ConsoleLogger();
        ConversionHistoryRepository repo = new InMemoryCHP();

        ConsoleComandParser consoleComandParser = new ConsoleComandParser(repo,logger, exchange);
        while (true) {
            String line = scanner.nextLine();
            String[] split = line.split("\\s+");
            consoleComandParser.execute(split);
        }
    }


}
