package sudoku.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuAlgorithmTest {

    String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
    Board board = new Board(9,9);

    @Test
    public void CheckIfElementIsInitial() {

        board.createBoard(input);

        assertTrue(board.initialValues[0][3]);
        assertFalse(board.initialValues[0][2]);
    }

    @Test
    public void CheckIfElementIsValid() {
        board.createBoard(input);

        assertFalse(board.checkValidElement(1,0,0,0));
        assertTrue(board.checkValidElement(4,0,0,0));
    }

    @Test
    public void CheckIfElementIsAdded() {
        board.createBoard(input);

        board.addElement(4,3,4,4);

        assertTrue(board.getRows().get(3)[4] == 4);
        assertTrue(board.getColumns().get(4)[3] == 4);
        assertTrue(board.getBlocks().get(4)[1] == 4);

    }

    @Test
    public void CheckIfElementIsDeleted() {
        board.createBoard(input);

        board.deleteElement(2,4,1);

        assertTrue(board.getRows().get(2)[4] == 0);
        assertTrue(board.getColumns().get(4)[2] == 0);
        assertTrue(board.getBlocks().get(1)[7] == 0);
    }

    @Test
    public void CheckIfFocusGoesBack() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(0,2, board.initialValues);

        focus.back();

        assertTrue(focus.getColumnNumber()==1);
        assertTrue(focus.getRowNumber()==0);

    }

    @Test
    public void CheckIfFocusGoesBackSkippingInitialValues() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(0,5, board.initialValues);

        focus.back();

        assertTrue(focus.getColumnNumber()==2);
        assertTrue(focus.getRowNumber()==0);

    }

    @Test
    public void CheckIfFocusGoesForward() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(0,1, board.initialValues);

        focus.forward();


        assertTrue(focus.getColumnNumber()==2);
        assertTrue(focus.getRowNumber()==0);

    }

    @Test
    public void CheckIfFocusGoesForwardSkippingInitialValues() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(0,2, board.initialValues);

        focus.forward();


        assertTrue(focus.getColumnNumber()==5);
        assertTrue(focus.getRowNumber()==0);

    }

    @Test
    public void CheckIfFocusGoesBackRow() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(1,0, board.initialValues);

        focus.back();

        assertTrue(focus.getColumnNumber()==8);
        assertTrue(focus.getRowNumber()==0);

    }

    @Test
    public void CheckIfFocusCannotGoBelowRow0() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(0,0, board.initialValues);

        assertThrows(RuntimeException.class, () -> focus.back());

    }

    @Test
    public void CheckIfFocusCannotGoAboveRow8() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(8,8, board.initialValues);

        assertThrows(RuntimeException.class, () -> focus.forward());

    }

    @Test
    public void CheckIfAlgorithmSkipsPreviousTriedValue() {

        board.createBoard(input);
        Focuselement focus = new Focuselement(0,0, board.initialValues);
        board.addElement(4,0,0,0);

        SudokuSolver.findElement(board, focus);
        assertTrue(board.getRows().get(0)[0] == 6);

    }

    @Test
    public void CheckIfAlgorithmStopsIfSolved() {

        board.createBoard(input);
        boolean Solved = true;

        SudokuSolver.solver(board);
        for (int[] row: board.getRows()) {
            for (int i=0; i<row.length; i++) {
                if (row[i]==0)
                    Solved = false;
            }
        }

        assertTrue(Solved);

    }

    @Test
    public void CheckIfAlgorithmStopsIfUnsolvable() {

        String wronginput = "100820090500000000308040007100000040006402503000090010093004000004035200000700900";
        board.createBoard(wronginput);
        boolean Solved = true;

        SudokuSolver.solver(board);
        for (int[] row: board.getRows()) {
            for (int i=0; i<row.length; i++) {
                if (row[i]==0)
                    Solved = false;
            }
        }

        assertFalse(Solved);

    }

}