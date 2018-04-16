package minesweeper;

import minesweeper.utils.OpenGame;
import minesweeper.utils.SaveGame;

import javax.swing.*;

/**
 * This class is for setting a menu with open and save options
 */
public class MinesweeperMenu {
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem open;
    private JMenuItem save;

    MinesweeperMenu() {
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        open = new JMenuItem("Open Game");
        save = new JMenuItem("Save Game");
    }

    public void initMinesweeperMenu() {
        open.addActionListener(new OpenGame());
        menu.add(open);
        save.addActionListener(new SaveGame());
        menu.add(save);
        menuBar.add(menu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
