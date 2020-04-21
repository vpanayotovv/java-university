package src;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        LinkedHashMap<String,String> phonebook = new LinkedHashMap<>();
        String input = scanner.nextLine();

        while (!input.equals("search")){
            String[] data = input.split("-");
            String name = data[0];
            String phone = data[1];
            phonebook.putIfAbsent(name,phone);
            phonebook.put(name,phone);
            input = scanner.nextLine();
        }
        input = scanner.nextLine();
        while (!input.equals("stop")){
            String name = input;
            if(phonebook.containsKey(name)){
                System.out.printf("%s -> %s\n", name , phonebook.get(name));
            }else {
                System.out.println("Contact " + name + " does not exist.");
            }
            input = scanner.nextLine();
        }
    }
}
