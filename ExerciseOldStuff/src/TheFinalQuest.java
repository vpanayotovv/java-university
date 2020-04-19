import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TheFinalQuest{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> words = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());
        String data = "";
        while (!"Stop".equals(data = scanner.nextLine())){
            String[] input = data.split("\\s+");
            String command = input[0];
            if(command.equals("Delete")){
                int index = Integer.parseInt(input[1]);
                if(index+1 <= words.size()-1 && index+1 > 0){
                    words.remove(index+1);
                }
            }

            if (command.equals("Swap")){
                String word1 = input[1];
                String word2 = input[2];
                if(words.contains(word1) && words.contains(word2)){
                    Collections.swap(words,words.indexOf(word1),words.indexOf(word2));
                }
            }
            if (command.equals("Sort")){
                Collections.sort(words);
                Collections.reverse(words);
            }
            if (command.equals("Put")){
                int index = Integer.parseInt(input[2]);
                if (index > 0 && index-1 <= words.size()) {
                    words.add(index - 1, input[1]);
                }
            }
            if (command.equals("Replace")){
                String word1 = input[1];
                String word2 = input[2];
                if (words.contains(word2)){
                    int indexWord2 = words.indexOf(word2);
                    words.set(indexWord2,word1);

                }
            }

        }
        System.out.println(String.join(" ", words));

    }
}
