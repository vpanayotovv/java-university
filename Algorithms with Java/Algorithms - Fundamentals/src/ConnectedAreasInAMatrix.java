import java.util.*;

public class ConnectedAreasInAMatrix {
    private static char[][] matrix;
    private static List<int[]> areas;
    private static int counter = 1;
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = Integer.parseInt(scanner.nextLine());
        int col = Integer.parseInt(scanner.nextLine());
        matrix = new char[row][col];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = scanner.nextLine().toCharArray();
        }
        areas = new ArrayList<>();
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] == '-') {
                        areas.add(new int[]{r, c, 0});
                    findPath(r, c);
                }
            }
        }
        builder.append("Total areas found: ").append(areas.size()).append(System.lineSeparator());
        areas.sort((f,s) -> {
            if (f[2] - s[2] != 0){
                return s[2] - f[2];
            }else {
                return f[2] - s[2];
            }
        });
        for (int[] area : areas) {
                builder.append(String.format("Area #%d at (%d, %d), size: %d",
                        counter++,
                        area[0],
                        area[1],
                        area[2])).append(System.lineSeparator());
        }
        System.out.println(builder.toString());

    }

    private static void findPath(int row, int col) {
        if (!isInBound(row, col) || matrix[row][col] == 'V' || matrix[row][col] == '*') {
            return;
        }
        matrix[row][col] = 'V';

        areas.get(areas.size() -1)[2]++;

        findPath(row + 1, col);
        findPath(row, col + 1);
        findPath(row - 1, col);
        findPath(row, col - 1);
    }

    private static boolean isInBound(int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}

// INOUT :
// 4
// 9
// ---*---*-
// ---*---*-
// ---*---*-
// ----*-*--