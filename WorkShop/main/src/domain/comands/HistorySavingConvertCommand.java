package domain.comands;

import domain.entities.Money;
import domain.external.Exchange;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

public class HistorySavingConvertCommand extends ConvertCommand {
    private final ConversionHistoryRepository repo;

    public HistorySavingConvertCommand(Exchange exchange, Logger logger, ConversionHistoryRepository repo) {
        super(exchange, logger);
        this.repo = repo;
    }

    @Override
    public void execute(Input input) {
        Money converted = protectedExecute(input);
        repo.save(input.getFrom(), converted);
    }

}
