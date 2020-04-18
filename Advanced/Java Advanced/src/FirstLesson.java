import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FirstLesson {
    public static void main(String[] args) throws IOException {
        String path = "ResourcesLAB/Files-and-Streams";

        File fileAndDirectories = new File(path);
        Deque<File> stack = new ArrayDeque<>();
        stack.push(fileAndDirectories);

        List<String> fileNames = new ArrayList<>();

        while (!stack.isEmpty()) {

            File file = stack.pop();
            File[] currentFolderConten = file.listFiles();
        }
    }
}