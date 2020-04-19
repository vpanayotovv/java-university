package oldPackeges.jediGalaxy;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readPosition(scanner.nextLine());
        Filed filed = new Filed(dimensions[0],dimensions[1]);
        Galaxy galaxy = new Galaxy(filed);

        String positions = scanner.nextLine();
        long collectedStars = 0;
        while (!positions.equals("Let the Force be with you")) {
            int[] jediPositions = readPosition(positions);
            int[] evilPositions = readPosition(scanner.nextLine());

            int rowEvil = evilPositions[0];
            int colEvil = evilPositions[1];
            int rowJedi = jediPositions[0];
            int colJedi = jediPositions[1];

            galaxy.moveEvil(rowEvil,colEvil);

            collectedStars += galaxy.moveJedi(rowJedi,colJedi);

            positions = scanner.nextLine();
        }

        System.out.println(collectedStars);


    }

    private static int[] readPosition(String s) {
        return Arrays.stream(s.split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }
}
