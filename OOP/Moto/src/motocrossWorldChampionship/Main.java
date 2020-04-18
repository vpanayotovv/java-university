package motocrossWorldChampionship;

import motocrossWorldChampionship.core.EngineImpl;
import motocrossWorldChampionship.core.interfaces.Engine;
import motocrossWorldChampionship.io.interfaces.ConsoleReader;
import motocrossWorldChampionship.io.interfaces.ConsoleWriter;

public class Main {
    public static void main(String[] args) {
        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        Engine engine = new EngineImpl(reader,writer);
        engine.run();
    }
}