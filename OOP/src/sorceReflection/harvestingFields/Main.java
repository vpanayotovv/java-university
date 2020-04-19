package sorceReflection.harvestingFields;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("HARVEST")) {
            String command = input;
            Class<RichSoilLand> refClass = RichSoilLand.class;
            switch (command) {
                case "private":
                    Arrays.stream(refClass
                            .getDeclaredFields())
                            .filter(m -> Modifier.isPrivate(m.getModifiers()))
                            .forEach(m -> System.out.println(String.format("private %s %s", m.getType().getSimpleName(), m.getName())));
                    break;
                case "protected":
                    Arrays.stream(refClass.getDeclaredFields())
                            .filter(m -> Modifier.isProtected(m.getModifiers()))
                            .forEach(m -> System.out.println(String.format("protected %s %s", m.getType().getSimpleName(), m.getName())));
                    break;
                case "public":
                    Arrays.stream(refClass.getDeclaredFields())
                            .filter(m -> Modifier.isPublic(m.getModifiers()))
                            .forEach(m -> System.out.println(String.format("public %s %s", m.getType().getSimpleName(), m.getName())));
                    break;
                case "all":
                    Arrays.stream(refClass.getDeclaredFields())
                            .forEach(m -> System.out.println(String.format("%s %s %s",Modifier.toString(m.getModifiers()), m.getType().getSimpleName(), m.getName())));
                    break;
            }
            input = scanner.nextLine();
        }
    }
}
