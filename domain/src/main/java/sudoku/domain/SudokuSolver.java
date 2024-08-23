package sudoku.domain;

public class SudokuSolver {

    public static void main(String[] args) {

    //String input = "000820090500000000308040007100000040006402503000090010093004000004035200000700900";
    String input = "130207006007080000004009003000090000800010302600700150901000000000070800700000201";
    Board board = new Board(9,9);
    board.createBoard(input);

    board.printBoard();
    long start = System.currentTimeMillis();
    solver(board);
    long finish = System.currentTimeMillis();
    board.printBoard();

    long timeElapsed = finish - start;
    System.out.println("Solved in " + timeElapsed + " milliseconds.");
    }

    public static void solver (Board board) {

        Focuselement focus = new Focuselement(0,0, board.initialValues);
        if (board.initialValues[0][0])
            focus.forward();
        while (true) {
            try {
                findElement(board, focus);
            }
            catch (RuntimeException e) {
                System.out.println(e);
                break;
            }
        }

    }

    public static void findElement(Board board, Focuselement focus) {

        int i = focus.getRowNumber();
        int j = focus.getColumnNumber();
        int currentValue = board.getRows().get(i)[j];

        board.deleteElement(i, j, board.findBlock(i, j));

        for (int n = (currentValue+1); n < 10; n++) {
            if (board.checkValidElement(n, i, j, board.findBlock(i, j))) {
                board.addElement(n, i, j, board.findBlock(i, j));
                break;
            }
        }
        if (board.getRows().get(i)[j]==0) {
            focus.back();
        } else
            focus.forward();
    }

}