package sorceReflection.barracksWars;

import sorceReflection.barracksWars.interfaces.Repository;
import sorceReflection.barracksWars.interfaces.Runnable;
import sorceReflection.barracksWars.interfaces.UnitFactory;
import sorceReflection.barracksWars.core.Engine;
import sorceReflection.barracksWars.core.factories.UnitFactoryImpl;
import sorceReflection.barracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
