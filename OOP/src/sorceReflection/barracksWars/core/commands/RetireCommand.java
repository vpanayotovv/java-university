package sorceReflection.barracksWars.core.commands;

import sorceReflection.barracksWars.interfaces.Repository;
import sorceReflection.barracksWars.interfaces.UnitFactory;

public class RetireCommand extends Command {
    protected RetireCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        try {
            this.getRepository().removeUnit(unitType);
            return unitType + " retired!";
        }catch (IllegalArgumentException ex){
            return ex.getMessage();
        }
    }
}
