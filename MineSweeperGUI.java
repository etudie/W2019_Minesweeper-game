/*****************************************************************
 Handles GUI of minesweeper, including pop up option panes

 @author Xue Hua
 @version Winter 2019
 *****************************************************************/
package MineSweeper;

import javax.swing.*;

public class MineSweeperGUI {
    /* size of board */
    static private int size;

    /* number of mines */
    static private int mine;


    /*****************************************************************
     Prompts user to enter a valid number. Will default to 10 if
     there is no input.
     @param void
     @return none
     @throws NumberFormatException if non integer input
     @throws NullPointerException if input is null
     *****************************************************************/
    static private void gameSizePrompt() {

        // sets up the OptionPane buttons
        UIManager.put("OptionPane.cancelButtonText", "QUIT");
        UIManager.put("OptionPane.okButtonText", "NEXT");
        String input = JOptionPane.showInputDialog(null,
                "Enter Size of board (3-30)\n" +
                        "[ Leaving blank defaults to 10 ]", "10");

        // Checks if valid size input
        try {

            // Defaults to 10 if blank
            if (input.equals(""))
                input = "10";

            // Once a button is selected
            if ((input != null) && (input.length() > 0))
                try {
                    size = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    errorMessage();
                }
            if (size < 2 || size > 30)
                errorMessage();
        } catch (NullPointerException e) {
            System.exit(1);
        }
        gameMinePrompt();
    }

    /*****************************************************************
     Prompts user to enter a valid number of mines. Will default to 10 if
     there is no input.
     @param void
     @return none
     @throws NumberFormatException if non integer input
     @throws NullPointerException if input is null
     *****************************************************************/
    static private void gameMinePrompt() {
        String input = JOptionPane.showInputDialog(null,
                "Mines (1 - " + size + " )\n " +
                        "[Leaving blank defaults to 10]", "10");
        // Checks if valid size input
        try {

            // Defaults to 10 if blank
            if (input.equals(""))
                input = "10";

            // Once a button is selected
            if ((input != null) && (input.length() > 0))
                try {
                    mine = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    errorMessage();
                }
            if (mine < 0 || mine > size * size)
                errorMessage();
        } catch (NullPointerException e) {
            System.exit(1);
        }
    }

    /*****************************************************************
     Displays an error message
     @param void
     @return none
     *****************************************************************/
    static private void errorMessage() {
        Object[] options = {"Gracefully Try Again", "Quit!"};
        int n = JOptionPane.showOptionDialog(null,
                "Invalid Input\n", "OOPS!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        if (n == JOptionPane.YES_OPTION)
            gameSizePrompt();
        else
            System.exit(0);
    }


    public static void main(String[] args) {

        // Prompt user for board size and number of mines
        gameSizePrompt();

        // Frame setup
        JFrame frame = new JFrame("Mine Sweeper!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MineSweeperPanel panel = new MineSweeperPanel(size, mine);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}

