import java.util.Scanner;
import java.util.TreeMap;

public class CountSymbols {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        TreeMap<Character,Integer> chars = new TreeMap<>();

        for (char symbol : input.toCharArray()) {
            chars.putIfAbsent(symbol,0);
            chars.put(symbol,chars.get(symbol) + 1);

        }
        chars.entrySet().forEach(e -> System.out.println(String.format("%c: %d time/s",e.getKey(),e.getValue())));


    }
}
