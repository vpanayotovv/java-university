package sorceReflection.barracksWars.core.commands;

import sorceReflection.barracksWars.interfaces.Repository;
import sorceReflection.barracksWars.interfaces.UnitFactory;

public class ReportCommand extends Command {
    protected ReportCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return getRepository().getStatistics();
    }
}
