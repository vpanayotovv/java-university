package sorceReflection.blackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> refClass = BlackBoxInt.class;
        Constructor<BlackBoxInt> ctor = refClass.getDeclaredConstructor();
        ctor.setAccessible(true);
        BlackBoxInt blackBoxRef = ctor.newInstance();

        List<String> methods = new ArrayList<>();
        for (Method declaredMethod : blackBoxRef.getClass().getDeclaredMethods()) {
            methods.add(declaredMethod.getName());

        }

        String input = scanner.nextLine();
        while (!input.equals("END")){
            String[] commands = input.split("_");
            String commandName = commands[0];
            int value = Integer.parseInt(commands[1]);
            Method[] methodsOfRef = blackBoxRef.getClass().getDeclaredMethods();

            for (Method method : methodsOfRef) {
                if (method.getName().equals(commandName)){
                    method.setAccessible(true);
                    method.invoke(blackBoxRef,value);
                }
            }

            Field innerValue = blackBoxRef.getClass().getDeclaredField("innerValue");
            innerValue.setAccessible(true);
            Object o = innerValue.get(blackBoxRef);
            String string = o.toString();
            Integer integer = Integer.valueOf(string);
            System.out.println(integer);

            input=scanner.nextLine();
        }
    }
}
