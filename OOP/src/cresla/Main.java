package cresla;

import cresla.entities.ManagerImpl;
import cresla.interfaces.Manager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Manager manager = new ManagerImpl();
        String input = scanner.nextLine();

        while (true) {
            String[] tokens = input.split("\\s+");
            String command = tokens[0];
            List<String> params = Arrays.stream(tokens).skip(1).collect(Collectors.toList());
            String output = "";
            if ("Reactor".equals(command)) {
                output = manager.reactorCommand(params);

            } else if ("Module".equals(command)) {
                output = manager.moduleCommand(params);

            } else if ("Report".equals(command)) {
                output = manager.reportCommand(params);

            } else if ("Exit".equals(command)) {
                break;
            }

            if (!output.isEmpty()){
                System.out.println(output);
            }

            input = scanner.nextLine();
        }
    }
}
