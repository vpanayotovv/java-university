package src;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class ReverseNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        ArrayDeque<String> input = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+")).forEach(input::push);

        while(!input.isEmpty()){
            System.out.print(input.pop() + " ");
        }


    }
}