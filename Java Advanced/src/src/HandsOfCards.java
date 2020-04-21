package src;

import java.util.*;

public class HandsOfCards {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Map<String, Set<String>> map = new LinkedHashMap<>();
        while (!input.equals("JOKER")) {
            String[] namesAndCards = input.split(": ");
            String name = namesAndCards[0];
            map.putIfAbsent(name, new LinkedHashSet<>());
            String[] cards = namesAndCards[1].split(", ");
            for (int i = 0; i < cards.length; i++) {
                map.get(name).add(cards[i]);
            }

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
                int deckPower = calcDeckPower(entry.getValue());
            System.out.println(String.format("%s: %d",entry.getKey(),deckPower));
        }
    }

    private static int calcDeckPower(Set<String> deck) {
        Map<Character, Integer> lookUpTableForLetters = Map.of(

                'J', 11,
                'Q', 12,
                'K', 13,
                'A', 14,
                'S', 4,
                'H',3,
                'D',2,
                'C',1,
                '1',10
        );
        int sumPower = 0;
        for (String card : deck) {
            int power = 0;
            if(Character.isDigit(card.charAt(0))){
                if(card.charAt(0) - '0' == 1){
                    power += 10;
                }else {
                    power += card.charAt(0) - '0';
                }
            }else {
                power += lookUpTableForLetters.get(card.charAt(0));

            }

            if(lookUpTableForLetters.containsKey(card.charAt(card.length()-1))){

                power *= lookUpTableForLetters.get(card.charAt(card.length()-1));
            }

            sumPower += power;
        }


        return sumPower;
    }
}
