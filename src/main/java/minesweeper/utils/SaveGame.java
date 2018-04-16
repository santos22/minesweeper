package minesweeper.utils;

import minesweeper.MinesweeperApplication;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveGame implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            try {
                FileUtils.writeStringToFile(fileChooser.getSelectedFile(),
                                            MinesweeperApplication.getInitialBoard().toString()
                                                    + "\n" +
                                                    MinesweeperApplication.getCurrentBoard().toString(),
                                            "UTF-8");
            } catch (IOException pE) {
                pE.printStackTrace();
            }
        }
    }
}