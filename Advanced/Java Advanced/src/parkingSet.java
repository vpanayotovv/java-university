import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class parkingSet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Set<String> parking = new LinkedHashSet<>();
        String input = scanner.nextLine();
        while (!"END".equals(input)) {
            String[] split = input.split(", ");
            String inOrOut = split[0];
            String carNumber = split[1];
            if ("IN".equals(inOrOut)) {
                parking.add(carNumber);
            } else {
                parking.remove(carNumber);
            }

            input = scanner.nextLine();

        }

        for (String s : parking) {
            System.out.println(s);
        }
        if(parking.isEmpty()){
            System.out.println("Parking Lot is Empty");
        }
    }
}
