package src;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Trojan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int waves = Integer.parseInt(scanner.nextLine());

        Deque<Integer> defenders = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
        Deque<Integer> attackers = new ArrayDeque<>();
        for (int i = 1; i < waves && !defenders.isEmpty(); i++) {
            Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(attackers::push);


            if (i % 3 == 0) {
                int addDefenders = Integer.parseInt(scanner.nextLine());
                defenders.offer(addDefenders);
            }
            while (!defenders.isEmpty() && !attackers.isEmpty()) {

                int attacker = attackers.pop();
                int deffender = defenders.poll();
                if (attacker > deffender) {
                    attacker -= deffender;
                    attackers.push(attacker);
                } else if (attacker < deffender) {
                    deffender -= attacker;
                    defenders.addFirst(deffender);

                }
            }
        }
        if(defenders.isEmpty()){
            System.out.println("The Trojans successfully destroyed the Spartan defense.");
            System.out.printf("Warriors left: %s%n",String.join(", ",attackers.toString()).replaceAll("\\[|\\]",""));
        }else {
            System.out.println("“The Spartans successfully repulsed the Trojan attack.”");
            System.out.printf("Plates left: %s%n",String.join(", ",defenders.toString()).replaceAll("\\[|\\]",""));
        }
    }
}
