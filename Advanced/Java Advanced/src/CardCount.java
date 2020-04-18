import java.util.*;

public class CardCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Set<String>> playersWithDecks = new LinkedHashMap<>();
        String input = scanner.nextLine();
        while (!input.equals("JOKER")){
            Arrays.stream(input.split("\\s+"));

            input = scanner.nextLine();
        }
    }
}
