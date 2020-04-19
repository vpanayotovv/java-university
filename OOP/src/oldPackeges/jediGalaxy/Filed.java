package oldPackeges.jediGalaxy;

public class Filed {
    private int[][] matrix;

    public Filed(int rows, int cols) {
        this.matrix = new int[rows][cols];
        this.fillValues(4);
    }

    private void fillValues(int beginValue){
        for (int row = 0; row < this.matrix.length; row++) {
            for (int col = 0; col < this.matrix[row].length; col++) {
               this.matrix[row][col] = beginValue++;
            }
        }
    }

    public boolean isInBounds(int row, int col) {
        return row >= 0 && row < this.matrix.length && col >= 0 && col < this.matrix[row].length;
    }

    public int setValue(int row, int col, int newValue) {
           return this.matrix[row][col] = newValue;
    }

    public int getLength() {
        return this.matrix.length;
    }

    public int getValue(int row, int col) {
        return this.matrix[row][col];
    }
}
