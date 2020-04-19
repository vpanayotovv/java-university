package sorceReflection.barracksWars.core.commands;

import sorceReflection.barracksWars.interfaces.Repository;
import sorceReflection.barracksWars.interfaces.Unit;
import sorceReflection.barracksWars.interfaces.UnitFactory;

public class AddCommand extends Command {
    protected AddCommand(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = this.getUnitFactory().createUnit(unitType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.getRepository().addUnit(unitToAdd);
        return unitType + " added!";
    }
}
