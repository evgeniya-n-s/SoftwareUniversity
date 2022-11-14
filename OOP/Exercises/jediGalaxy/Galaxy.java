package jediGalaxy;

public class Galaxy {
    private Field field;

    public Galaxy(Field field){
        this.field = field;
    }

    public void moveSith(int row, int col) {
        while (row >= 0 && col >= 0) {
            if (field.isInBounds(row,col)) {
                field.setValue(row, col,0);
            }
            row--;
            col--;
        }
    }

    public long moveJedi(int row, int col) {
        long collectedPower = 0;
        while (row >= 0 && col < this.field.getCollection(1)) {
            if (this.field.isInBounds (row,col)) {
                collectedPower += this.field.getValue(row,col);
            }
            col++;
            row--;
        }
        return collectedPower;
    }
}
