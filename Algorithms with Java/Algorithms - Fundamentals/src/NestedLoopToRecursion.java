import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NestedLoopToRecursion {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[] arr = new int[n];

        permute(arr,0);
    }

    private static void permute(int[] arr, int index) {
        if (index == arr.length){
            print(arr);
        }
        else {
            for (int i = 1; i <= arr.length; i++) {
                arr[index] = i;
                permute(arr,index + 1);
            }
        }
    }

    private static void print(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
        System.out.println();
    }
}
