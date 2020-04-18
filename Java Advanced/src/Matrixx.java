import java.util.Scanner;

    public class Matrixx {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int[][] matrix = readInput(scanner);
            int maxSum = Integer.MIN_VALUE;
            int maxSumRow = 0;
            int maxSumCol = 0;

            for (int row = 0; row < matrix.length -1; row++) {
                for (int col = 0; col < matrix[row].length -1; col++) {

                    int sum = matrix[row][col] + matrix[row][col + 1]
                            + matrix[row + 1][col] + matrix[row + 1][col + 1];

                    if(sum > maxSum){
                        maxSum = sum;
                        maxSumRow = row;
                        maxSumCol = col;

                    }
                }
            }

            System.out.println(matrix[maxSumRow][maxSumCol] + " " + matrix[maxSumRow][maxSumCol+1]);
            System.out.println(matrix[maxSumRow + 1][maxSumCol] + " " + matrix[maxSumRow + 1][maxSumCol + 1]);
            System.out.println(maxSum);
        }

        private static int[][] readInput(Scanner scanner) {
            String[] line = scanner.nextLine().split( ",\\s+");
            int rows = Integer.parseInt(line[0]);
            int cols = Integer.parseInt(line[1]);



            int[][] marix  = new int[rows][cols];

            for (int row = 0; row < rows; row++) {
                String[] currentLine = scanner.nextLine().split( ",\\s+");
                for (int col = 0; col < cols; col++) {
                    marix[row][col] = Integer.parseInt(currentLine[col].trim());
                }
            }
            return marix;

        }
    }