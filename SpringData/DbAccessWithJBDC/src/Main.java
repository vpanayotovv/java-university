import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ProblemSolver problemSolver = new ProblemSolver();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username for your DB:");
        String username = scanner.nextLine();


        System.out.println("Enter password for your DB:");
        String password = scanner.nextLine();

        try {
            UserPasswordRepo repo = new UserPasswordRepo(username, password);
            problemSolver.setConnection(repo.getUsername(), repo.getPassword());
            System.out.println("Enter number of problem u wanna check:");

            String numberOfProblem = scanner.nextLine();

            switch (numberOfProblem) {
                case "2":
                    problemSolver.getVillainsNamesPr02();
                    break;
                case "3":
                    problemSolver.getMinionNamesPr03();
                    break;
                case "4":
                    problemSolver.addMinionPr04();
                    break;
                case "5":
                    problemSolver.changeTownNamesCasingPr05();
                    break;
                case "6":
                    problemSolver.removeVillainPr06();
                    break;
            }

        } catch (IllegalArgumentException | SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }
}
