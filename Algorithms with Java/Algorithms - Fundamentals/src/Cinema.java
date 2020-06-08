import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cinema {
    private static String[] seats;
    private static String[] freelancePeople;
    private static StringBuilder builder = new StringBuilder();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> people = Arrays.stream(scanner.nextLine().split(",\\s+")).collect(Collectors.toList());
        seats = new String[people.size()];
        String input = scanner.nextLine();
        while (!input.equals("generate")){
            String[] token = input.split(" - ");
            String name = token[0];
            int number = Integer.parseInt(token[1]) - 1;
            seats[number] = name;
            people.remove(name);
            input = scanner.nextLine();
        }
        freelancePeople = new String[people.size()];
        for (int i = 0; i < people.size(); i++) {
            freelancePeople[i] = people.get(i);
        }
        permute(0);
        System.out.println(builder.toString().trim());
    }

    private static void permute(int index) {
        if (index == freelancePeople.length) {
            print();
            return;
        }
        permute(index + 1);
        for (int i = index + 1; i < freelancePeople.length; i++) {
            swap(freelancePeople, index,i);
            permute(index + 1);
            swap(freelancePeople, index,i);
        }
    }

    private static void print() {
        int index = 0;
        for (String seat : seats) {
            if (seat != null) {
                builder.append(seat).append(" ");
            } else {
                builder.append(freelancePeople[index++]).append(" ");
            }
        }
        builder.append(System.lineSeparator());
    }

    private static void swap(String[] arr, int first, int second) {
        String temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
// INPUT:
// Garry, Liam, Teddy, Anna, Buddy, Simon
// Buddy - 3
// Liam - 5
// Simon - 1
// generate
