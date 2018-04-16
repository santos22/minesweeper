# Minesweeper

![alt text](https://i.imgur.com/Gcrl9ci.png "Minesweeper screenshot")

This was an attempt to recreate a modified version of Minesweeper. The board will reveal one of three states when a player clicks a tile:
* Danger Tile (red) - the tile hides a mine
* Caution Tile (yellow) - the tile is adjacent to a mine
* Safe Tile (green) - the tile is neither adjacent to or hides a mine

## Features
- [x] Allow the player to specify the mine field dimensions - number of columns and rows
- [x] Allow the player to choose a difficulty level of easy, medium, or hard
- [x] End the game when a mine tile is selected

## Running the program
Run the following command in the root directory:
```
java -jar minesweeper.jar
```

## Improvements
- [ ] Save game state and continue later
- [ ] Include a timer that forces the player to make a selection within a pre-determined number of seconds
- [ ] A Caution Tile also includes the number of adjacent mines - e.g. 1 if only 1 mine is in immediate proximity
- [ ] Two-player support with turns. The last player standing wins or the game is tied
