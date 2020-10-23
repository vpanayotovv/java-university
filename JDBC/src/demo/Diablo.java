package demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Diablo {
    public static void main(String[] args) {
        Properties properties = new Properties();
        String path = Diablo.class.getClassLoader().getResource("jdbc.properties")
                .getPath();

        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username:");
        String username = scanner.nextLine().trim();
        if (username.isBlank()) {
            username = "Stamat";
        }

        try (Connection connection = DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.user"),
                properties.getProperty("db.password"))) {
            PreparedStatement stmt = connection.prepareStatement(properties.getProperty("db.sqlquery"));

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("user_name") == null) {
                    System.out.println("DB user not found!");
                    break;
                }
                System.out.printf("User: %s%n%s %s has played %d games",
                        resultSet.getString("user_name"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("count"));


            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
