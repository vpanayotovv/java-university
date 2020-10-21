package demo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DB_URL = "jdbc:mysql://localhost:3306/soft_uni";

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
            System.out.println(String.format("%s Not Found!", DB_DRIVER));
            System.exit(0);
        }

        System.out.println("DB Driver loaded successfully!");

        Properties properties = new Properties();

        properties.setProperty("user", username);
        properties.setProperty("password", password);

        try (Connection connection = DriverManager.getConnection(DB_URL, properties)) {


            System.out.println("Connection created successfully!");

            System.out.println("Enter minimum Salary:");
            String salary = scanner.nextLine().trim();
            salary = salary.length() > 0 ? salary : "40000";
            double salaryInt;

            salaryInt = Double.parseDouble(salary);

            PreparedStatement stmt =
                    connection.prepareStatement(
                            "select * from employees where salary > ? order by employee_id"
                    );

            stmt.setDouble(1, salaryInt);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("| %10d | %-15.15s | %-15.15s | %10.2f |%n",
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDouble("salary")
                );
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
