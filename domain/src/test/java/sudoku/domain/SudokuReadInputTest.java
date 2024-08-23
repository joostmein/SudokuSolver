package sudoku.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuReadInputTest {

    String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
    Board board = new Board(9,9);

    @Test 
    public void ReadRowsTest() {
        board.createBoard(input);
        int[] row0 = {0, 0, 0, 8, 2, 0, 0, 9, 0};
        int[] row4 = {0, 0, 6, 4, 0, 2, 5, 0, 3};
        int[] row8 = {0, 0, 0, 7, 0, 0, 9, 0, 0};

        assertArrayEquals(board.getRows().get(0), row0);
        assertArrayEquals(board.getRows().get(4), row4);
        assertArrayEquals(board.getRows().get(8), row8);
    }


    @Test
    public void ReadColumnsTest() {

        board.createBoard(input);
        int[] column0 = {0, 5, 3, 1, 0, 0, 0, 0, 0};
        int[] column4 = {2, 0, 4, 0, 0, 9, 0, 3, 0};
        int[] column8 = {0, 0, 7, 0, 3, 0, 0, 0, 0};

        assertArrayEquals(board.getColumns().get(0), column0);
        assertArrayEquals(board.getColumns().get(4), column4);
        assertArrayEquals(board.getColumns().get(8), column8);
    }

    @Test
    public void ReadBlocksTest() {

        board.createBoard(input);
        int[] block0 = {0, 0, 0, 5, 0, 0, 3, 0, 8};
        int[] block4 = {0, 0, 0, 4, 0, 2, 0, 9, 0};
        int[] block8 = {0, 0, 0, 2, 0, 0, 9, 0, 0};

        assertArrayEquals(board.getBlocks().get(0), block0);
        assertArrayEquals(board.getBlocks().get(4), block4);
        assertArrayEquals(board.getBlocks().get(8), block8);
    }

}