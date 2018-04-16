package minesweeper;

import java.awt.*;

/**
 * This class sets the color of a grid tile
 */
public enum BoardColor {

    LIGHT_GRAY(Color.lightGray, "Grey", "gr"), // Default
    GREEN(Color.green, "Green", "g"), // Safe
    RED(Color.red, "Red", "r"), // Mine
    YELLOW(Color.yellow, "Yellow", "y"); // Caution

    private Color color;
    private String name;
    private String shortName;

    BoardColor(Color color, String name, String shortName) {
        this.color = color;
        this.name = name;
        this.shortName = shortName;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return shortName;
    }

}


