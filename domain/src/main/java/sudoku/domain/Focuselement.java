package sudoku.domain;

public class Focuselement {

    private int rowNumber;

    private int columnNumber;

    private final boolean[][] initialValues;

    public Focuselement(int rowNumber, int columnNumber, boolean[][] initialValues) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.initialValues = initialValues;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void back() throws RuntimeException{

        if (columnNumber > 0)
            columnNumber--;
        else if (rowNumber > 0) {
            rowNumber--;
            columnNumber = 8;
        }
        else
            throw new RuntimeException("Sudoku is unsolvable");
        if (initialValues[rowNumber][columnNumber])
            back();
    }

    public void forward()throws RuntimeException {

        if (columnNumber < 8)
            columnNumber++;
        else if (rowNumber < 8) {
            rowNumber++;
            columnNumber = 0;
        }
        else
            throw new RuntimeException("Sudoku is solved!");
        if (initialValues[rowNumber][columnNumber])
            forward();
    }
}
