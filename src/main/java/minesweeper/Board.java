package minesweeper;

import minesweeper.enums.Difficulty;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board {

    private static int numMines;
    private int[][] board;

    Board(int x, int y) {
        board = new int[x][y];
    }

    /**
     * EASY: 20%
     * MEDIUM: 33.33%
     * HARD/DEFAULT: 50%
     */
    public Board setMines(Difficulty pDifficultyEnum, int x, int y) {
        switch (pDifficultyEnum) {
            case EASY:
                numMines = (x * y) / 5;
                break;
            case MEDIUM:
                numMines = (x * y) / 3;
                break;
            default:
                numMines = (x * y) / 2;
                break;
        }
        setMineLocations(x, y, board);

        // Debugging
        System.out.println(this.toString());
        return this;
    }

    /**
     * Randomizes location of mines on board based on grid size.
     * A 5x5 board with medium difficulty, will have 8 mines
     * A 5x5 board with hard difficulty, will have 12 mines
     */
    private static void setMineLocations(int x, int y, int[][] board) {
        // Create range of random numbers from 0 to (x*y) - 1
        List<Integer> range = IntStream.rangeClosed(0, (x * y) - 1)
                                       .boxed().collect(Collectors.toList());
        Collections.shuffle(range);

        // Choose a set of unique random numbers from shuffled range
        Set<Integer> mineLocations = new LinkedHashSet<>(range.subList(0, numMines));

        // Use set of mine locations to set mines on the board
        int mineLocation = 0;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (mineLocations.contains(mineLocation)) {
                    board[i][j] = 2;
                }
                mineLocation++;
            }
        }
    }

    /**
     * Sets saved location of mines on board based on initial state
     * of the board when saved game was initially created
     */
    public Board setSavedMineLocations(String[] initialState) {
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length(); j++) {
                // Grab tile state from the saved file
                int tileState = Character.getNumericValue(initialState[i].charAt(j));
                if (tileState == 2) { // Set mine
                    board[i][j] = tileState;
                    numMines++;
                }
            }
        }
        return this;
    }

    /**
     * Sets saved location of safe and caution tiles on board
     * based on state of the board when the game was saved
     */
    public Board setSavedCautionSafeLocations(String[] currentState) {
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState[i].length(); j++) {
                int tileState = Character.getNumericValue(currentState[i].charAt(j));
                if (tileState == 1) {
                    board[i][j] = tileState;
                }
            }
        }
        return this;
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        for (int[] aBoard : board) {
            for (int j = 0; j < board[0].length; j++) {
                boardString.append(aBoard[j]);
            }
            boardString.append(";"); // Add ; as a delimiter between rows
        }
        return boardString.toString();
    }

    /**
     * Displays state of the tile that was clicked
     */
    public BoardColor getColorOfTile(int x, int y) {
        if (board[x][y] == 2) {
            return BoardColor.RED; // Mine
        }

        if (isAdjacentToMine(x, y)) {
            return BoardColor.YELLOW; // Caution
        }

        return BoardColor.GREEN; // Safe
    }

    /**
     * Checks if any adjacent tile is a mine
     */
    private boolean isAdjacentToMine(int x, int y) {
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                try {
                    if (board[i][j] == 2) {
                        return true;
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    // Ignore exception
                }
        return false;
    }

    public void setBoardValue(int x, int y, int val) {
        board[x][y] = val;
    }

    public int getMaxScore() {
        return (board.length * board[0].length) - numMines;
    }
}
