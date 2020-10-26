import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

class ProblemSolver {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/";
    private static final String SCHEMA_NAME = "minions_db";

    private static Scanner scanner;


    private Connection connection;


    private String getEntityNameById(int entityID, String tableName) throws SQLException {
        String query = String.format("select name " +
                "from %s " +
                "where id = ?", tableName);

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, entityID);

        ResultSet resultSet = ps.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;

    }

    private int getEntityIdByName(String entityName, String tableName) throws SQLException {
        String query = String.format("select id from %s where name = ?", tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, entityName);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next() ? resultSet.getInt(1) : -1;
    }

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
        scanner = new Scanner(System.in);
        System.out.println("Enter Villain ID:");
        int id = Integer.parseInt(scanner.nextLine());

        String villainName = getEntityNameById(id, "villains");
        if (villainName == null) {
           throw new SQLException("No villain with ID " + id + " exists in the database.");
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

    public void problem04() throws SQLException {
        System.out.println("Minion:");
        scanner = new Scanner(System.in);
        String[] minionInfo = scanner.nextLine().split("\\s+");
        String name = minionInfo[0];
        int age = Integer.parseInt(minionInfo[1]);
        String townName = minionInfo[2];
        System.out.println("Villain:");
        String villainName = scanner.nextLine();

        int townId = getEntityIdByName(townName, "towns");

        if (townId == -1){
            insertInTable(townName);
            throw new SQLException(String.format("Town %s was added to the database.",townName));
        }

        int minionId = getEntityIdByName(name, "minions");

        if (minionId == -1){
            insertInTable(name,age,townName);
            throw new SQLException(String.format("Successfully added %s to be minion of %s.",name,villainName));
        }


        if (isVillainExist(villainName)){
            //TODO:Can't handle this case :(
        }else {
            insertInTableVillain(villainName);
            throw new SQLException(String.format("Villain %s was added to the database.",villainName));
        }

    }

    private void insertInTableVillain(String villainName) throws SQLException {
        String query = "insert into villains(name,evilness_factor) value(?,'evil')";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,villainName);
        preparedStatement.execute();
    }

    private void insertInTable(String minionName , int age,String townName) throws SQLException {
        String query = "insert into minions(name,age,town_id) value(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,minionName);
        preparedStatement.setInt(2,age);
        preparedStatement.setInt(3,getEntityIdByName(townName,"towns"));
        preparedStatement.execute();
    }

    private void insertInTable(String townName) throws SQLException {
        String query = "insert into towns(name) value(?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,townName);
        preparedStatement.execute();
    }

    private boolean isVillainExist(String name) throws SQLException {
        String query = "select name from villains where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1,name);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }
}
