/*****************************************************************
 Returns cell state of minesweeper

 @author Xue Hua
 @version Winter 2019
 *****************************************************************/
package MineSweeper;

public class Cell {

    /** Checks if Cell is exposed */
    private boolean isExposed;

    /** Checks if Cell is a mine */
    private boolean isMine;

    /** Checks if Cell is flagged */
    private boolean isFlagged;

    /*****************************************************************
     Constructor that sets the initial state of each cell when called.
     @param exposed     sets cell to exposed
     @param mine        sets cell to mine
     @param flagged     sets cell to flagged
     *****************************************************************/
    public Cell(boolean exposed, boolean mine, boolean flagged) {
        isExposed = exposed;
        isMine = mine;
        isFlagged = flagged;
    }

    /*****************************************************************
     Returns if cell is exposed
     @param void
     @return true if it is exposed
     *****************************************************************/
    public boolean isExposed() {
        return isExposed;
    }

    /*****************************************************************
     Sets cell as exposed
     @param exposed   set true to expose
     @return none
     *****************************************************************/
    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }

    /*****************************************************************
     Returns if cell is a mine
     @param void
     @return true if it is a mine
     *****************************************************************/
    public boolean isMine() {
        return isMine;
    }

    /*****************************************************************
     Sets cell as a mine
     @param mine   set true to set as mine
     @return none
     *****************************************************************/
    public void setMine(boolean mine) {
        isMine = mine;
    }

    /*****************************************************************
     Returns if cell is flagged
     @param void
     @return true if it is flagged
     *****************************************************************/
    public boolean isFlagged() {
        return isFlagged;
    }

    /*****************************************************************
     Sets cell as flagged
     @param flagged   set true to set as flagged
     @return none
     *****************************************************************/
    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
