import java.util.Arrays;
import java.util.List;

public class VarArgs {
    public static void main(String[] args) {
        display("pesho","gosho","stamat");
    }
    public static void display(String... varArgs){
        List<String> strings = Arrays.asList(varArgs);

        for (String string : strings) {
            System.out.println(string);
        }
    }
}
