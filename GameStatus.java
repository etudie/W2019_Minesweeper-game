/*****************************************************************
 Handles GameStatus of mineSweeper.

 @author Xue Hua
 @version Winter 2019
 *****************************************************************/
package MineSweeper;

/*****************************************************************
 Returns GameStatus.
 @param void
 @return Lost       Game is lost
         Won        Game is won
        NotOverYet  Game is not won and not lost
 *****************************************************************/
public enum GameStatus {
    Lost, Won, NotOverYet
}
