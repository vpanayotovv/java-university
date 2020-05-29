import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PathsInLabyrinth {
    private static List<Character> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = Integer.parseInt(scanner.nextLine());
        int cols = Integer.parseInt(scanner.nextLine());
        char[][] labyrinth = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            labyrinth[i] = scanner.nextLine().toCharArray();
        }

        findPath(labyrinth, 0, 0, ' ');
    }

    private static void findPath(char[][] labyrinth,int row,int col,char direction){
        if (!isInBounds(labyrinth, row, col)) {
            return;
        }
        path.add(direction);

        if (isExit(labyrinth, row, col)) {
            printPath();
        }else if (!isVisited(labyrinth, row, col) && isPassable(labyrinth, row, col)) {

            mark(labyrinth, row, col);

            findPath(labyrinth, row, col + 1, 'R'); // Right​

            findPath(labyrinth, row + 1, col, 'D'); // Down​

            findPath(labyrinth, row, col - 1, 'L'); // Left​

            findPath(labyrinth, row - 1, col, 'U'); // Up​

            unmark(labyrinth, row, col);
        }
        path.remove(path.size()-1);
    }

    private static boolean isPassable(char[][] labyrinth, int row, int col) {
        return labyrinth[row][col] == '-';
    }

    private static boolean isVisited(char[][] labyrinth, int row, int col) {
        return labyrinth[row][col] == 'V';
    }

    private static void unmark(char[][] labyrinth, int row, int col) {
        labyrinth[row][col] = '-';
    }

    private static void printPath() {
        for (int i = 1; i < path.size(); i++) {
            System.out.print(path.get(i));
        }
        System.out.println();
    }

    private static void mark(char[][] labyrinth, int row, int col) {
        labyrinth[row][col] = 'V';
    }

    private static boolean isExit(char[][] labyrinth, int row, int col) {
        return labyrinth[row][col] == 'e';
    }

    private static boolean isInBounds(char[][] labyrinth, int row, int col) {
        return row < labyrinth.length && row >= 0 && col < labyrinth[row].length && col >= 0;
    }
}
// INPUT :
// 3
// 5
// -**-e
// -----
// *****
//
// OUTPUT :
// DRRRRU
// DRRRUR

