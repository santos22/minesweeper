package minesweeper.utils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class OpenGame implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        // Demonstrate "Open" dialog:
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            System.out.println(file.length());
        }
    }
}