# C163P2 - MineSweeper Game (README in progress)
http://minesweeperonline.com/
#### Components
- Java 

**Objective 1** : Make a functional minesweeper game with Model View Controller (MVC) design

**Objective 2** : Practive recursion in Java

#### Cell.java
Contains setter and getter methods to check and update the following boolean variables: 
•	public Cell getCell(int row, int col) this method returns a Cell for a given row and col on the board so the panel can make the appropriate display.   
Variable | Function 
---   | ---   
**isExposed**   | if Cell is exposed
**isMine** | if a Cell is a mine
**isFlagged**  | if a Cell is flagged

#### GameStatus.java
this method is called from the MineSweeperPanel class and it determines if a player has won the game after the select method (see above) was called. (The following are standard MineSweeper rules):
o	Return a GameStatus. Lost, if player selects a mine
o	Return a GameStatus.Won,  if player has exposed all non-mine locations
o	Return a GameStatus. NotOverYet if the previous rules do not apply


enum | Status 
---   | ---   
**Lost**   | Game Lost
**Won** | Game Won
**NotOverYet**  | Anything else

#### MineSweeperGame.java
This class handles ALL of the game activities
#### MineSweeperPanel.java
#### MineSweeperGUI.java
**Function**

Color | 8-Bit    | Mask| Result| Color change
---   | ---   | ---|---|--
Red   | 0001  | 0011 |0010| Red > Green
Green | 0010  |0110 |0100| Green > Blue
Blue  | 0100  |0101 |0001| Blue > Red

### Part 2
- Similar to part 1, but work with the MSP timer.
- So, when the button is held, the lights should change. In comparison to previously staying on the current color. 

#### Execution
- Using **SysTick**
````#include "msp.h"

int main(void) {
    /* initialize P2.0 for red LED */
    P2->SEL1 &= ~1;             /* configure P2.0 as simple I/O */
    P2->SEL0 &= ~1;
    P2->DIR |= 1;               /* P2.0 set as output */

    /* Configure SysTick */
    SysTick->LOAD = 3000000-1;  /* reload reg. with 3,000,000-1 */
    SysTick->VAL = 0;           /* clear current value register */
    SysTick->CTRL = 5;          /* enable it, no interrupt, use master clock */

    while (1) {
        if (SysTick->CTRL & 0x10000)    /* if COUNTFLAG is set */
            P2->OUT ^= 1;               /* toggle red LED */
    }
}
````

