package sorceReflection.barracksWars.core.factories;

import sorceReflection.barracksWars.interfaces.Unit;
import sorceReflection.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;
import sorceReflection.barracksWars.models.units.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "sorceReflection.barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) throws ClassNotFoundException {

        Class<?> refClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
        try {
            Constructor<?> ctor = refClass.getDeclaredConstructor();
            Object unit = ctor.newInstance();
            if (unit instanceof Unit){
                return (Unit) unit;
            }
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

}
