package src;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class fileStream {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\vpanayotvo\\IdeaProjects\\Java Advanced\\ResourcesLAB\\input.txt";
        String output = "output2.txt";


        List<String> allLines = Files.lines(Path.of(path)).sorted().collect(Collectors.toList());
        Files.write(Path.of(output),allLines);


    }
}
