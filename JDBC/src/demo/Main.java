package demo;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username:");
        String username = scanner.nextLine().trim();
        username = username.length() > 0 ? username : "root";
        System.out.println("Enter Password:");
        String password = scanner.nextLine().trim();
        password = password.length() > 0 ? password : "";

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(String.format("%s Not Found!",DB_DRIVER));
            System.exit(0);
        }

        System.out.println("DB Driver loaded successfully!");
    }
}
