package sorceReflection.barracksWars.core.commands;

import sorceReflection.barracksWars.interfaces.Repository;
import sorceReflection.barracksWars.interfaces.UnitFactory;

public class FightCommand extends Command {
    protected FightCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return "fight";
    }
}
