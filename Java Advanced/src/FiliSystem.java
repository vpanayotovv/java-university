import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FiliSystem {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\vpanayotvo\\IdeaProjects\\Java Advanced\\ResourcesExersice\\input.txt";

        long sum = 0;
        for (byte bytes : Files.readAllBytes(Path.of(path))) {
            sum += bytes;
        }
        System.out.println(sum);
    }
}
