package src;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BrowserHistory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> browser = new ArrayDeque<>();
        String input = "";
        while (!"Home".equals(input = scanner.nextLine())){
            if(!"back".equals(input)){
                browser.push(input);
                System.out.println(input);
            }else {
                if(browser.size() > 1){
                    browser.pop();
                    System.out.println(browser.peek());
                }else {
                    System.out.println("no previous URLs");
                }
            }
        }
    }
}
