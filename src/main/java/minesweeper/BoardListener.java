package minesweeper;


import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is for receiving mouse events
 */
public class BoardListener extends MouseAdapter {
    private Grid mineGrid;

    BoardListener(Grid pMineGrid) {
        this.mineGrid = pMineGrid;
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            mineGrid.tileClicked((JLabel) e.getSource());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            BoardColor[][] boardColors = mineGrid.getBoardColors();
            for (BoardColor[] boardColor : boardColors) {
                for (BoardColor aBoardColor : boardColor) {
                    System.out.print(aBoardColor + " ");
                }
            }
        }
        System.out.println(MinesweeperApplication.getCurrentBoard().toString());
    }
}