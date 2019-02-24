package MineSweeper;

import java.util.Random;

public class MineSweeperGame {
    /** board size */
    public int size;
    /**
     * number of mines
     */
    public int mine;
    public int won = 0;
    public int loss = 0;
    public int games = 0;
    /**
     * board
     */
    private Cell[][] board;
    /**
     * game status
     */
    private GameStatus status;
    /**
     * number of exposed mines
     */
    private int num;
    /**
     * virtual board
     */
    private int[][] mineCount;

    /* CONSTRUCTOR */
    public MineSweeperGame(int inputsize, int inputmine) {
        size = inputsize;
        mine = inputmine;
        status = GameStatus.NotOverYet;
        board = new Cell[size][size];
        mineCount = new int[size][size];
        reset();

    }

    /*****************************************************************
     Method that resets the board, clearing the num Count and the
     relaying the mines.
     @param none
     inputMine number of mines
     *****************************************************************/
    public void reset() {
        status = GameStatus.NotOverYet;
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++) {
                board[r][c] = new Cell(false, false, false);
                mineCount[r][c] = 0;
            }
        num = 0;
        layMines(mine);
        mineCount();
    }

    /*****************************************************************
     Getter method for current cell.
     @param row number of current row
     col number of current col
     @return board[row][cell] the current cell of the board
     *****************************************************************/
    public Cell getCell(int row, int col) {
        return board[row][col];
    }

    /*****************************************************************
     Getter method for neighbouring mines on current Cell
     @param row number of current row
     col number of current col
     @return mineCount[row][cell] neighbouring mines on current Cell
     *****************************************************************/
    public int getMineCount(int row, int col) {
        return mineCount[row][col];
    }

    /*****************************************************************
     Setter method to flag current cell
     @param row number of current row
     col number of current col
     @return none;
     *****************************************************************/
    public void flag(int row, int col) {
        board[row][col].setFlagged(true);
    }

    /*****************************************************************
     Method that checks if game is lost, won or still in motion
     @param row number of current row
     col number of current col
     @return none;
     *****************************************************************/
    public void select(int row, int col) {
        if (board[row][col].isMine() && !board[row][col].isFlagged()) {
            loss++;
            games++;
            status = GameStatus.Lost;
        }
        recursiveMineCount(row, col);
        // Non-Recursive version
				/*for(int r = row-1 ; r <= row+1 ; r++)
					for(int c = col - 1 ; c <= col+1 ; c++)
						if (r >= 0 && r < size && c  >= 0 && c < size) {
								if(!board[r][c].isMine())
                                board[r][c].setExposed(true);*/
        if (ifWinGame()) {
            won++;
            games++;
            status = GameStatus.Won;
        }
    }

    /*****************************************************************
     Checker method that counts how many cells are exposed
     @param none;
     @return true if all non-mines are exposed;
     *****************************************************************/
    private boolean ifWinGame() {
        num = 0;
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (board[r][c].isExposed() && !board[r][c].isMine())
                    num++;
        return (num >= (size * size) - mine);
    }

    /*****************************************************************
     Helper method that recursively seeks neighbouring mines. Stops
     recursion when it encounters a mine or neighbour of a mine.
     Moves in four directions. (Up, Down, Left, Right)
     @param row number of current row
     col number of current col
     @return none
     *****************************************************************/
    private void recursiveMineCount(int row, int col) {

        // Check bounds of board
        if (row < 0 || row >= size || col < 0 || col >= size
                || board[row][col].isFlagged() || board[row][col].isMine())
            return;

        if (mineCount[row][col] == 0 && !board[row][col].isExposed()) {
            System.out.println("trying to reveal board [" + row + "][" + col + "]");
            if (!board[row][col].isFlagged())

                // Reveal '0's
                board[row][col].setExposed(true);
            System.out.println("reveal success board[" + row + "][" + col + "]");
        } else if (mineCount[row][col] > 0 && !board[row][col].isExposed()) {
            System.out.println("Blocked at[" + row + "][" + col + "]");
            if (!board[row][col].isFlagged())

                // Reveal mineCount if not flagged
                board[row][col].setExposed(true);
            return;
        } else {

            // Stop if there is a mine
            System.out.println("stopped");
            return;
        }

        recursiveMineCount(row - 1, col);
        recursiveMineCount(row + 1, col);
        recursiveMineCount(row, col + 1);
        recursiveMineCount(row, col - 1);
    }

    /*****************************************************************
     Helper method that counts if there are neigbouring mines.
     @param none
     @return none
     *****************************************************************/
    private void mineCount() {
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (board[r][c].isMine())
                    for (int row = r - 1; row <= r + 1; row++)
                        for (int col = c - 1; col <= c + 1; col++)
                            if (row >= 0 && row < size && col >= 0 && col < size)
                                mineCount[row][col]++;
    }

    /*****************************************************************
     Getter method for gameStatus.
     @param none
     @return status     gameStatus so far
     *****************************************************************/
    public GameStatus getGameStatus() {
        return status;
    }

    /*****************************************************************
     Helper method to set mines randomly
     @param mineCount   number of mines
     @return none
     *****************************************************************/
    private void layMines(int mineCount) {
        int i = 0;        // ensure all mines are set in place
        Random random = new Random();
        while (i < mineCount) {            // perhaps the loop will never end :)
            int c = random.nextInt(size);
            int r = random.nextInt(size);

            if (!board[r][c].isMine()) {
                board[r][c].setMine(true);
                i++;
            }
        }
    }


}


//  a non-recursive from .... it would be much easier to use recursion


