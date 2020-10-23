import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

class ProblemSolver {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA_NAME = "minions_db";


    private Connection connection;


    public void setConnection(String username, String password) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        connection = DriverManager.getConnection(CONNECTION_STRING + SCHEMA_NAME, properties);

    }

    public void problem02() throws SQLException {
        String query = "select name , count(minion_id) as count " +
                "from villains as v " +
                "join minions_villains v2 on v.id = v2.villain_id " +
                "group by v.id " +
                "having count > 15 " +
                "order by count desc ";

        PreparedStatement ps = connection.prepareStatement(query);

        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("count"));
        }
    }

    public void problem03() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Villain ID:");
        int id = Integer.parseInt(scanner.nextLine());

        String villainName = getEntityByName(id,"villains");
        if (villainName == null){
            System.out.println("No such villain");
            return;
        }

        String query = "select m.name ,m.age " +
                "from minions as m join minions_villains v on m.id = v.minion_id " +
                "where v.villain_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int counter = 1;
        System.out.println("Villain: " + villainName);
        while (resultSet.next()) {
            System.out.printf("%d. %s %d%n", counter++, resultSet.getString("name"),
                    resultSet.getInt("age"));
        }
    }

    private String getEntityByName(int entityID, String tableName) throws SQLException {
        String query = String.format("select name " +
                "from %s " +
                "where id = ?", tableName);

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, entityID);

        ResultSet resultSet = ps.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;

    }
}
