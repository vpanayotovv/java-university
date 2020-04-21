package src;

import java.util.*;
import java.util.stream.Collectors;

public class LootBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> firstLoot = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(ArrayDeque::new));
        Deque<Integer> secondLoot = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(secondLoot::push);
        int lootSum = 0;
        while (!firstLoot.isEmpty() && !secondLoot.isEmpty()){
            int sum = firstLoot.peek() + secondLoot.peek();
            if (sum % 2 != 0){
                secondLoot.pop();

            }else {
                firstLoot.pop();
                secondLoot.pop();
                lootSum += sum;
            }
            if(firstLoot.isEmpty()){
                System.out.println("First lootbox is empty");
            }else if (secondLoot.isEmpty()){
                System.out.println("Second lootbox is empty");
            }
        }
        if (lootSum >= 100){
            System.out.println("Your loot was epic! Value: " + lootSum);
        }else {
            System.out.println("Your loot was poor... Value: " + lootSum);
        }
    }
}
