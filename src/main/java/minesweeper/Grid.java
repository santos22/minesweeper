package minesweeper;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This class initializes a container with a grid of tiles
 */
@SuppressWarnings("serial")
public class Grid extends JPanel {
    private BoardColor[][] boardColors;
    private JLabel[][] myLabels;

    /**
     * Constructs new grid of tiles for a new game
     * or grid of tiles from a saved game
     */
    Grid(int rows, int cols, int tileWidth, int tileHeight, boolean savedGame) {
        boardColors = new BoardColor[rows][cols];
        myLabels = new JLabel[rows][cols];

        BoardListener myListener = new BoardListener(this);
        Dimension labelPrefSize = new Dimension(tileWidth, tileHeight);
        setLayout(new GridLayout(rows, cols)); // 2 for padding
        for (int row = 0; row < myLabels.length; row++) {
            for (int col = 0; col < myLabels[row].length; col++) {
                JLabel myLabel = new JLabel();
                myLabel.setOpaque(true);
                myLabel.setBorder(new LineBorder(Color.black));
                BoardColor boardColor = BoardColor.LIGHT_GRAY;
                boardColors[row][col] = boardColor;
                myLabel.setBackground(boardColor.getColor());
                myLabel.addMouseListener(myListener);
                myLabel.setPreferredSize(labelPrefSize);
                add(myLabel);
                if (savedGame) {
                    myLabel.setBackground(MinesweeperApplication.getInitialBoard().getColorOfTile(row, col).getColor());
                }
                myLabels[row][col] = myLabel;
            }
        }
    }

    public BoardColor[][] getBoardColors() {
        return boardColors;
    }

    /**
     * Reveals the state/color of a tile when it is clicked
     * and ends game if tile uncovered is a mine
     */
    public void tileClicked(JLabel label) {
        for (int row = 0; row < myLabels.length; row++) {
            for (int col = 0; col < myLabels[row].length; col++) {
                if (label == myLabels[row][col]) {
                    BoardColor tileState = MinesweeperApplication.getInitialBoard().getColorOfTile(row, col);

                    // Change color of tile on grid to reveal state
                    boardColors[row][col] = tileState;
                    myLabels[row][col].setBackground(tileState.getColor());

                    // Set the board value of the clicked tile to 1 (e.g. 1 means it has been clicked already)
                    MinesweeperApplication.getCurrentBoard().setBoardValue(row, col, 1);

                    // Exit if tile clicked is a mine
                    if (tileState == BoardColor.RED) {
                        showMessageDialog(null, "Game over! You lost. Score: " + MinesweeperApplication.getScore());
                        System.exit(0);
                    } else { // Increment score for non-mine tile
                        MinesweeperApplication.incrementScore();
                    }

                    // Exit if max score is reached (e.g. no mines were uncovered)
                    if (MinesweeperApplication.getScore() == MinesweeperApplication.getCurrentBoard().getMaxScore()) {
                        showMessageDialog(null, "Game over! You won. Score: " + MinesweeperApplication.getScore());
                        System.exit(0);
                    }
                }
            }
        }
    }
}