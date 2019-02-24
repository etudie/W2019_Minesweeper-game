package MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class MineSweeperPanel extends JPanel {

    /* Array of JButtons that represent the board */
    private JButton[][] board;

    /* Quit Button */
    private JButton butQuit;

    /* Current cell State  */
    private Cell iCell;

    /* Current number of wins */
    private JLabel winLbl;

    /* Current number of losses */
    private JLabel lossLbl;

    /* Current number of played games */
    private JLabel playedLbl;

    /* Model for gameplay */
    private MineSweeperGame game;

    /* Size of board */
    private int size;

    /*****************************************************************
     Constructor creates a board of specified size with specified
     mines.
     @param inputSize size of board
     inputMine number of mines
     *****************************************************************/
    public MineSweeperPanel(int inputSize, int inputMine) {
        size = inputSize;

        // Instantiate JPanels
        JPanel bottom = new JPanel();
        JPanel center = new JPanel();

        // Create game listeners
        ButtonListener listener = new ButtonListener();

        // Instantiate game
        game = new MineSweeperGame(inputSize, inputMine);

        // Instantiate labels, buttons and places them
        lossLbl = new JLabel("Losses : " + game.loss);
        winLbl = new JLabel("Wins : " + game.won);
        playedLbl = new JLabel("Games : " + game.games);
        butQuit = new JButton("Quit");
        butQuit.addActionListener(listener);
        bottom.add(butQuit);
        bottom.add(playedLbl);
        bottom.add(winLbl);
        bottom.add(lossLbl);


        // Create the board
        center.setLayout(new GridLayout(size, size));
        board = new JButton[size][size];

        // Span the minefield
        for (int row = 0; row < size; row++)
            for (int col = 0; col < size; col++) {

                // Span each minefield with a blank JButton or Icon
                board[row][col] = new JButton("", new ImageIcon("src\\images\\facingdown.png"));
                board[row][col].addActionListener(listener);
                final int finalRow = row;
                final int finalCol = col;

                // Add MouseListener to each button for right click/flagging
                board[row][col].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)) {
                            flagMine(finalRow, finalCol);
                        }
                    }
                });

                // JButton customization
                board[row][col].setContentAreaFilled(false);
                board[row][col].setPreferredSize(new Dimension(20, 20));
                board[row][col].setFont(new Font("Arial", Font.BOLD, 5));

                // Add each button to JPanel
                center.add(board[row][col]);
            }

        displayBoard();

        bottom.setLayout(new GridLayout(3, 2));

        // Add all to contentPane
        add(new JLabel("MineSweeper"), BorderLayout.CENTER);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    /*****************************************************************
     Helper method that updates the minefield
     @param none
     @return none
     *****************************************************************/
    private void displayBoard() {

        // Updates win, loss and games played JLabels
        updateGameLbls();

        //
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++){

                // Fetches the Cell state of the current cell
                iCell = game.getCell(r, c);
                if (!iCell.isFlagged() && !iCell.isMine()) {

                    // Set faced down if not flagged
                    board[r][c].setIcon(iconSet(9));
                } else
                    board[r][c].setEnabled(true);
                if (iCell.isMine() && !iCell.isFlagged()) {

                    // Set mine if not flagged
                    //board[r][c].setText("!");
                    board[r][c].setIcon(iconSet(9));
                }
                if (iCell.isExposed()) {

                    // Set disabled is Game exposes cell
                    board[r][c].setEnabled(false);

                    // Expose how close neighbouring mines are or none
                    if (game.getMineCount(r, c) > 0) {
                        //board[r][c].setText("" + game.getMineCount(r, c));
                        board[r][c].setDisabledIcon(iconSet(game.getMineCount(r, c)));
                    } else
                        board[r][c].setDisabledIcon(iconSet(0));
                } else

                    // Default state is keeping Cell as enabled
                    board[r][c].setEnabled(true);
            }
    }

    /*****************************************************************
     Helper method that updates the game labels
     @param none
     @return none
     *****************************************************************/
    private void updateGameLbls(){
        playedLbl.setText("Played: " + game.games);
        winLbl.setText("Won: " + game.won);
        lossLbl.setText("Lost: " + game.loss);
    }

    /*****************************************************************
     Class that implements actionListener for gameplay
     @param none
     @return none
     *****************************************************************/
    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            // Quit button
            if (butQuit == e.getSource())
                System.exit(0);

            // Trigger Game when Cell is selected
            for (int r = 0; r < size; r++)
                for (int c = 0; c < size; c++)
                    if (board[r][c] == e.getSource()) {
                        game.select(r, c);
                    }

            // Updates with each trigger
            displayBoard();

            // Player loses
            if (game.getGameStatus() == GameStatus.Lost) {
                displayBoard();
                JOptionPane.showMessageDialog(null, "You Lose \n The game will reset");
                //exposeMines = false;
                game.reset();
                displayBoard();
            }

            // Player wins
            if (game.getGameStatus() == GameStatus.Won) {
                JOptionPane.showMessageDialog(null, "You Win: all mines have been found!\n The game will reset");
                game.reset();
                displayBoard();
            }
        }
    }

    /*****************************************************************
     Helper method to flag or un-flag mines upon right click on cell
     @param row number of rows
     col number of columns
     @return none
     *****************************************************************/
    private void flagMine(int row, int col) {

        // Check if cell is valid to flag
        if (board[row][col].isEnabled())

            // Check if cell was previously flagged
            if (game.getCell(row, col).isFlagged()) {

                // Un-flag if previously flagged
                game.getCell(row, col).setFlagged(false);

                // Sets it back to a appropriate icon
                if (game.getCell(row, col).isMine())
                    board[row][col].setIcon(iconSet(10));
                else
                    board[row][col].setIcon(iconSet(9));
            }else {

                // Flag if not already flagged
                game.getCell(row, col).setFlagged(true);
                game.flag(row, col);

                // Set to flag icon. 11 is 'flag'
                board[row][col].setIcon(iconSet(11));
            }
    }

    /*****************************************************************
     Helper method to set icons throughout the game.
     @param i index of icons array to represent corresponding icon
     @return icon ImageIcon type to set icon
     *****************************************************************/
    private ImageIcon iconSet(int i) {

        // Array of icons
        String[] icons = {"0.png", "1.png", "2.png", "3.png", "4.png", "5.png", "6.png",
                "7.png", "8.png", "facingdown.png", "bomb.png", "flagged.png"};
        ImageIcon icon = new ImageIcon("src\\MineSweeper\\" + icons[i]);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        return icon;
    }

}



