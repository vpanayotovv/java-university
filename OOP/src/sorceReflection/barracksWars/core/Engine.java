package sorceReflection.barracksWars.core;

import sorceReflection.barracksWars.interfaces.*;
import sorceReflection.barracksWars.interfaces.Runnable;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
    private final static String PATH_FOR_COMMAND = "sorceReflection.barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public Engine(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        while (true) {
            try {
                String input = reader.readLine();
                String[] data = input.split("\\s+");
                String commandName = data[0];
                String result = interpretCommand(data, commandName);
                if (result.equals("fight")) {
                    break;
                }
                System.out.println(result);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // TODO: refactor for problem 4
    private String interpretCommand(String[] data, String commandName) {
        String result = "";
        try {
            Class<?> command = Class.forName(PATH_FOR_COMMAND + Character.toUpperCase(commandName.charAt(0))
                    + commandName.substring(1) + "Command");
            Constructor<?> ctor = command.getDeclaredConstructor(String[].class, Repository.class, UnitFactory.class);
            ctor.setAccessible(true);
            Executable executable = (Executable) ctor.newInstance(data,this.repository,this.unitFactory);
            result = executable.execute();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
