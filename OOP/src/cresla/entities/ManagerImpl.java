package cresla.entities;

import cresla.interfaces.*;
import cresla.interfaces.Module;
import cresla.models.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager {

    private int id;
    private Map<Integer, Reactor> reactors;
    private Map<Integer,Module> modules;

    public ManagerImpl() {
        this.id = 1;
        this.reactors = new HashMap<>();
        this.modules = new HashMap<>();
    }

    @Override
    public String reactorCommand(List<String> arguments) {

        String type = arguments.get(0);
        int additionalParameter =Integer.parseInt(arguments.get(1));
        int moduleCapacity = Integer.parseInt(arguments.get(2));

        Reactor reactor;
        if (type.equals("Cryo")){
            reactor = new CryoReactor(id,moduleCapacity,additionalParameter);
        }else {
            reactor = new HeatReactor(id,moduleCapacity,additionalParameter);
        }

        this.reactors.putIfAbsent(id,reactor);
        return String.format("Created %s Reactor - %d",type, id++);
    }

    @Override
    public String moduleCommand(List<String> arguments) {
        //{reactorId} {type} {additionalParameter}
        int reactorId = Integer.parseInt(arguments.get(0));
        String type = arguments.get(1);
        int additionalParameter = Integer.parseInt(arguments.get(2));
        //“CryogenRod”, “HeatProcessor” or “CoolingSystem”.
        Module module = null;
        if (type.equals("CryogenRod")){
            module = new CryogenRod(id,additionalParameter);
        }else if (type.equals("HeatProcessor")){
            module = new HeatProcessor(id,additionalParameter);
        }else if (type.equals("CoolingSystem")){
            module = new CooldownSystem(id,additionalParameter);
        }
        if (type.equals("CryogenRod")){
            this.reactors.get(reactorId).addEnergyModule((EnergyModule) module);
        }else {
            this.reactors.get(reactorId).addAbsorbingModule((AbsorbingModule) module);
        }

        return String.format("Added %s - %d to Reactor - %d",type,id++,reactorId);
    }

    @Override
    public String reportCommand(List<String> arguments) {
        return null;
    }

    @Override
    public String exitCommand(List<String> arguments) {
        return null;
    }
}
