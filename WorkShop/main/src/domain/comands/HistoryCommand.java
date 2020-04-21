package domain.comands;

import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.util.List;

public class HistoryCommand implements Command<HistoryCommand.Input> {

    private final ConversionHistoryRepository repo;
    private final Logger logger;

    public HistoryCommand(ConversionHistoryRepository repo, Logger logger) {
        this.repo = repo;
        this.logger = logger;
    }


    @Override
    public void execute(Input input) {
        List<String> lastNCommands = repo.getLast(input.getCommandToShow());
        lastNCommands.forEach(logger::log);
    }

    public static class Input extends EmptyInput{
        private final int commandToShow;

        public Input(int commandToShow) {
            if (commandToShow < 1){
                throw new IllegalArgumentException("input must be positive integer");
            }
            this.commandToShow = commandToShow;
        }

        public int getCommandToShow() {
            return this.commandToShow;
        }
    }

}
