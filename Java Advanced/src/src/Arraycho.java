package src;

import java.util.Arrays;
    import java.util.Scanner;
    import java.util.TreeSet;

    public class Arraycho {
        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            scanner.nextLine();
            TreeSet<String> names = new TreeSet<>();


            while (n-- > 0){

                names.addAll(Arrays.asList(scanner.nextLine().split("\\s+")));
            }

            for (String s : names) {
                System.out.print(s + " ");

            }

        }

    }
