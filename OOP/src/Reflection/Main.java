package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<Reflection> reflectionClass = Reflection.class;
        Method getZip = reflectionClass.getDeclaredMethod("getZip");
        Constructor<Reflection> declaredConstructor = reflectionClass.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Reflection reflection = declaredConstructor.newInstance();
        System.out.println(getZip.invoke(reflection));


    }
}
