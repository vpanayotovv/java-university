package oldPackeges.PriceCalc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] input = splitInput(new Scanner(System.in));
        Reservation reservation = new Reservation(Double.parseDouble(input[0])
                ,Integer.parseInt(input[1])
                ,Season.valueOf(input[2].toUpperCase())
                ,DiscountType.valueOf(input[3].toUpperCase()));

        System.out.println(String.format("%.2f",reservation.calc()));

    }

    private static String[] splitInput(Scanner scanner) {
        return scanner.nextLine().split("\\s+");
    }
}
