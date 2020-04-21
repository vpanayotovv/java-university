package src;

import java.util.HashMap;
import java.util.Map;

public class guessList {
    public static void main(String[] args) {
        Map<String, Integer> myMap = new HashMap<>();
        myMap.put("Alice", 22);
        myMap.put("Bob",20);


        System.out.println(myMap.containsKey("Alice"));

        for (Map.Entry<String, Integer> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + " e na " + entry.getValue());
        }
    }
}
