import java.sql.SQLException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws SQLException {

        ProblemSolver problemSolver = new ProblemSolver();
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Enter username:");
//        String username = scanner.nextLine();
//
//        System.out.println("Enter password:");
//        String password = scanner.nextLine();

        problemSolver.setConnection("vasko","vasko");
        //problemSolver.problem02();
        problemSolver.problem03();
    }
}
