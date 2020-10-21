package demo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    private final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String DB_URL = "jdbc:mysql://localhost:3306/soft_uni";
    private final static String SQL_QUERY = "select * from employees where salary > ? order by employee_id";

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
            String salaryString = scanner.nextLine().trim();
            salaryString = salaryString.length() > 0 ? salaryString : "40000";
            double salary;

            salary = Double.parseDouble(salaryString);

            PreparedStatement stmt =
                    connection.prepareStatement(SQL_QUERY);

            stmt.setDouble(1, salary);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.printf("| %10d | %-15.15s | %-15.15s | %10.2f |%n",
                        rs.getLong("employee_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDouble("salaryString")
                );
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
