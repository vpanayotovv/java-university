package oldPackeges.jediGalaxy;

public class Galaxy {
    private Filed filed;

    public Galaxy(Filed filed) {
        this.filed = filed;
    }

    public void moveEvil(int row, int col) {
        while (row >= 0 && col >= 0) {
            if (filed.isInBounds(row, col)) {
                filed.setValue(row, col, 0);
            }
            row--;
            col--;
        }
    }

    public long moveJedi(int row, int col) {
        long collectedStars = 0;
        while (row >= 0 && col < this.filed.getLength()) {
            if (filed.isInBounds(row, col)) {
                collectedStars += this.filed.getValue(row,col);
            }

            col++;
            row--;
        }
        return collectedStars;
    }
}
