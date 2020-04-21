package src;

import java.util.*;

public class Sets {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        list.add("Peter");
        list.add("Ani");

        Set<String> newSet = new TreeSet<>(list);

        newSet.add("Alice");
        newSet.add("Bob");
        newSet.add("Anthony");

        for (String s : newSet) {
            System.out.println("Приятено ми е " + s);
        }
        System.out.println(String.join(" --> ",newSet));
    }
}
