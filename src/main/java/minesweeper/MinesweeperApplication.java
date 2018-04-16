package minesweeper;

import minesweeper.enums.Difficulty;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MinesweeperApplication {

    private static int x;
    private static int y;
    private static Board initialBoard; // Initial board configuration
    private static Board currentBoard; // Current board, with user actions
    private static int score = 0;
    private static boolean savedGame = false;

    private static final int MAX_X = 25;
    private static final int MAX_Y = 25;
    private static final int MIN_X = 2;
    private static final int MIN_Y = 2;

    private static void createGame() {
        Grid mainPanel = new Grid(x, y, 40, 40, savedGame);

        JFrame frame = new JFrame("GoodWater: Minesweeper");

        MinesweeperMenu minesweeperMenu = new MinesweeperMenu();
        minesweeperMenu.initMinesweeperMenu();
        frame.setJMenuBar(minesweeperMenu.getMenuBar());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.getContentPane().add(mainPanel);

        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Continue saved game? Y or N");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        String input = scanner.nextLine();

        if (input.equals("y") || input.equals("Y")) {
            openSavedGame();
            SwingUtilities.invokeLater(MinesweeperApplication::createGame);
        } else {
            startNewGame();
            SwingUtilities.invokeLater(MinesweeperApplication::createGame);
        }
    }

    /**
     * Opens a saved game from a text file
     */
    public static void openSavedGame() {
        savedGame = true;
        // NOTE: Currently, file dialog opens and players can choose where to
        // save the game - but opening a game works only if you exit after
        // saving a game to the project directory
        File file = new File("game.txt");
        try {
            List lines = FileUtils.readLines(file, "UTF-8");
            String[] initialBoardState = lines.get(0).toString().split(";"); // initial board state before save
            String[] currentBoardState = lines.get(1).toString().split(";"); // board state after save
            x = initialBoardState.length;
            y = initialBoardState[0].length();
            initialBoard = new Board(x,y).setSavedMineLocations(initialBoardState);
            currentBoard = new Board(x,y).setSavedCautionSafeLocations(currentBoardState);
        } catch (IOException ex) {
            System.err.println("Caught IOException: " + ex.getMessage());
        }
    }

    /**
     * Gets user input to determine mine field dimensions
     */
    private static void startNewGame() {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        boolean valid = false;
        while (!valid) {
            System.out.println("Enter dimensions as x,y for the board");
            String input = scanner.nextLine();
            if (input.length() < 3) {
                continue;
            }

            x = parseInt(input.split(",")[0]);
            y = parseInt(input.split(",")[1]);
            if (x <= MAX_X && y <= MAX_Y && x >= MIN_X && y >= MIN_Y) {
                valid = true;
            }
        }

        chooseDifficulty();
    }

    /**
     * Initializes the grid of tiles with mines based on the
     * difficulty chosen
     */
    public static void chooseDifficulty() {
        System.out.println("Enter 0 for Easy, 1 for Medium, 3 for Hard");
        Scanner scanner = new Scanner(System.in, "UTF-8");
        int choice = parseInt(scanner.nextLine());
        switch (choice) {
            case 0:
                initialBoard = new Board(x, y).setMines(Difficulty.EASY, x, y);
                break;
            case 1:
                initialBoard = new Board(x, y).setMines(Difficulty.MEDIUM, x, y);
                break;
            default:
                initialBoard = new Board(x, y).setMines(Difficulty.HARD, x, y);
                break;
        }
        currentBoard = new Board(x, y);
    }

    public static Board getInitialBoard() {
        return initialBoard;
    }

    public static Board getCurrentBoard() {
        return currentBoard;
    }

    public static int getScore() {
        return score;
    }

    public static void incrementScore() {
        score++;
    }
}