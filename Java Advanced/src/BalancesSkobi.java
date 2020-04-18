import java.util.ArrayDeque;
import java.util.Scanner;

public class BalancesSkobi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Character> stack = new ArrayDeque<>();
        String lookUpTable = "({[";
        String skobi = scanner.nextLine();
        boolean isBalances = true;
        for (int i = 0; i < skobi.length(); i++) {
            char symbol = skobi.charAt(i);
            if (lookUpTable.contains(symbol + "")){
                stack.push(symbol);
            }else {
                if(stack.isEmpty()){
                    isBalances = false;
                    break;
                }
                char top = stack.pop();
                if(!(top == '(' && symbol == ')' || top == '{' && symbol == '}' || top == '[' && symbol == ']' )){
                    isBalances = false;
                    break;
                }
            }
        }

        if (!isBalances){
            System.out.println("NO");
        }else {
            System.out.println("YES");
        }
    }
}
