package sudoku.domain;

import java.util.ArrayList;

public class Board  {

    private final int rowSize;
    private final int columnSize;
    private ArrayList<int[]> rows = new ArrayList<>();
    private ArrayList<int[]> columns = new ArrayList<>();
    private ArrayList<int[]> blocks = new ArrayList<>();
    public boolean[][] initialValues;

    public Board(int rowSize, int columnSize) {

        this.rowSize = rowSize;
        this.columnSize = columnSize;
        initialValues = new boolean[columnSize][rowSize];
    }

    public ArrayList<int[]> getRows() {
        return rows;
    }

    public ArrayList<int[]> getColumns() {
        return columns;
    }

    public ArrayList<int[]> getBlocks() {
        return blocks;
    }

    public void setRows(ArrayList<int[]> rows) {
        this.rows = rows;
    }

    public void setColumns(ArrayList<int[]> columns) {
        this.columns = columns;
    }

    public void setBlocks(ArrayList<int[]> blocks) {
        this.blocks = blocks;
    }

    public boolean checkValidElement(int element, int row, int column, int block) {
        return checkValidRow(element, row) && checkValidColumn(element, column) && checkValidBlock(element, block);

    }

    public boolean checkValidRow(int element, int row) {

        for (int i = 0; i < rowSize; i++) {
            if (rows.get(row)[i] == element)
                return false;
        }
        return true;

    }

    public boolean checkValidColumn(int element, int column) {

        for (int i = 0; i < columnSize; i++) {
            if (columns.get(column)[i] == element)
                return false;
        }
        return true;

    }

    public int findBlock(int row, int column) {
        return column / 3 + 3 * (row / 3);

    }

    public boolean checkValidBlock(int element, int block) {

        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(block)[i] == element)
                return false;
        }
        return true;

    }

    public void deleteElement(int row, int column, int block) {
        rows.get(row)[column] = 0;
        columns.get(column)[row] = 0;
        blocks.get(block)[column % 3 + 3 * (row % 3)] = 0;
    }

    public void addElement(int element, int row, int column, int block) {
        rows.get(row)[column] = element;
        columns.get(column)[row] = element;
        blocks.get(block)[column % 3 + 3 * (row % 3)] = element;
    }

    public void createBoard(String input) {

        createRows(input);
        createColumnsFromRows();
        createBlocksFromRows();
        setInitialValues();
    }


    public void createRows(String input) {
        for (int i = 0; i < columnSize; i++) {
            int[] row = new int[9];
            for (int j = 0; j < rowSize; j++) {
                row[j] = input.substring(9 * i, 9 * (i + 1)).charAt(j) - '0';
            }
            rows.add(row);
        }
    }

    public void createColumnsFromRows() {
        for (int i = 0; i < rowSize; i++) {
            int[] column = new int[9];
            for (int j = 0; j < columnSize; j++) {
                column[j] = rows.get(j)[i];
            }
            columns.add(column);
        }
    }

    public void createBlocksFromRows() {
        for (int i = 0; i < columnSize; i++) {
            int[] block = new int[9];
            for (int j = 0; j < rowSize; j++) {
                block[j] = rows.get(findBlock(i, j))[3 * (i % 3) + (j % 3)];
            }
            blocks.add(block);
        }
    }

    public void setInitialValues() {

        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (rows.get(i)[j] != 0)
                    initialValues[i][j] = true;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                System.out.print(rows.get(i)[j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}

