package src;

import java.util.List;

public class MainCode {

    public static int countSmileys(List<String> arr) {
        List<String> lookUpTable = List.of(":)", ":D", ";-D", ":~)");
        int count = 0;
        for (int i = 0; i < arr.size(); i++) {
            if (lookUpTable.contains(arr.get(i))){
                count ++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        List<String> lookUpTable = List.of(":)", ":D", ";-D", ":~)");
        System.out.println(countSmileys(lookUpTable));
    }
}