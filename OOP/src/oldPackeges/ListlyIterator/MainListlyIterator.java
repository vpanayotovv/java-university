package oldPackeges.ListlyIterator;

import java.util.Arrays;
import java.util.Scanner;

public class    MainListlyIterator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strings = Arrays.stream(scanner.nextLine().split("\\s+")).skip(1).toArray(String[]::new);

        ListyIterator listyIterator = new ListyIterator(strings);
        String input = scanner.nextLine();
        while (!input.equals("END")) {
            switch (input) {
                case "Move":
                    System.out.println(listyIterator.move());
                    break;
                case "HasNext":
                    System.out.println(listyIterator.hasNext());
                    break;
                case "Print":
                    try {
                        System.out.println(listyIterator.Print());
                    }catch (UnsupportedOperationException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "PrintAll":
                    for (String string : listyIterator) {
                        System.out.print(string + " ");
                    }
                    System.out.println();
                    break;
            }
            input = scanner.nextLine();
        }
    }
}