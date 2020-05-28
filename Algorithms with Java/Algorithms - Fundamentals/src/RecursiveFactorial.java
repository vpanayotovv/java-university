import java.util.Scanner;

public class RecursiveFactorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        long result = calcFact(num);
        System.out.println(result);
    }

    private static long calcFact(int num) {
        if (num == 1){
            return 1;
        }

        return num * calcFact(num - 1);
    }
}
