# C163P2 - MineSweeper Game 

![alt text](https://i.imgur.com/RzONGT9.png)
#### Components
**Objective 1** : Make a functional minesweeper game with Model View Controller (MVC) design

**Objective 2** : Practive recursion in Java

#### Cell.java
Contains setter and getter methods to check and update the following boolean variables: 

Variable | Function 
---   | ---   
**isExposed**   | if Cell is exposed
**isMine** | if a Cell is a mine
**isFlagged**  | if a Cell is flagged

#### GameStatus.java
This method is called from the MineSweeperPanel class and it determines if a player has won the game after the select method (see above) was called. (The following are standard MineSweeper rules):

- Return a GameStatus. Lost, if player selects a mine
- Return a GameStatus.Won,  if player has exposed all non-mine locations
- Return a GameStatus. NotOverYet if the previous rules do not apply

enum | Status 
---   | ---   
**Lost**   | Game Lost
**Won** | Game Won
**NotOverYet**  | Anything else

#### MineSweeperGame.java
This class handles ALL of the game activities

methods | function 
---   | ---   
**select(int row, int col)**   | What happens when a cell is selected
**getMineCount(int row, int col)** | Returns number of neighbouring mines
**recursion(int row, int col)**  | Uses "0-fill" algorithm to reveal empty cells

#### MineSweeperPanel.java
Panel incorporates MineSweeperGame

methods | function 
---   | ---   
**displayBoard()**   | Spans minesweeper board
**iconSet(int i)** | Sets icon for each cell using array
**flagMine(int row, int col)**  | Method that toggles cells

#### MineSweeperGUI.java
GUI calles the panel and other elements of the game.

methods | function 
---   | ---   
**gameSizePrompt()**   | Prompts for size of board



