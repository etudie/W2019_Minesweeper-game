package MineSweeper;


public class Cell {
    private boolean isExposed;
    private boolean isMine;
    private boolean isFlagged;

    public Cell(boolean exposed, boolean mine, boolean flagged) {
        isExposed = exposed;
        isMine = mine;
        isFlagged = flagged;
    }

    public boolean isExposed() {
        return isExposed;
    }

    public void setExposed(boolean exposed) {
        isExposed = exposed;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }
    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
