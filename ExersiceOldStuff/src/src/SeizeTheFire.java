package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeizeTheFire {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("#");
        int water = Integer.parseInt(scanner.nextLine());
        List<Integer> fires = new ArrayList<>();
        double effort = 0;
        int totalFire = 0;

        for (String cell : input) {
            String[] split = cell.split(" = ");
            String type = split[0];
            int value = Integer.parseInt(split[1]);
           if (isValid(type,value) && water >= value){
               water -= value;
               fires.add(value);
               totalFire += value;
               effort += value * 0.25;
           }
        }
        System.out.println("Cells:");
        for (Integer fire : fires) {
            System.out.println(" - " + fire);
        }
        System.out.println(String.format("Effort: %.2f",effort));
        System.out.println("Total Fire: " + totalFire);
    }
    private static boolean isValid(String type ,int value){
        switch (type){
            case "High":
                return 81 <= value && value <= 125;
            case "Medium":
                return 51 <= value && value <= 80;
            case "Low":
                return 1 <= value && value <= 50;
                default:
                    return false;
        }
    }
}
