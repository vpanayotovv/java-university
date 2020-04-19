package oldPackeges.Encapsulation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Team team = new Team("Eagles");

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            try {
                team.addPlayer(PersonParser.parsonFrom(line));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }

        String format = String.format("First team have %d players", team.getFirstTeam().size());
        System.out.println(format);
        String format2 = String.format("Reserve team have %d players",team.getReserveTeam().size());
        System.out.println(format2);

    }
}