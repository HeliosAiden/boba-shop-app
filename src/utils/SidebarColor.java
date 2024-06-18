package utils;

import java.awt.Color;

public class SidebarColor {

    public static Color getActiveColor(int level) {
        String colorCode;
        colorCode = switch (level) {
            case 0 -> "EAFAF1";
            case 1, 2, 3 -> "EAFAF1";
            default -> "FFFFFF";
        };
        return Color.decode("#" + colorCode);
    }

    public static Color getInactiveColor(int level) {
        String colorCode;
        colorCode = switch (level) {
            case 0 -> "D5DBDB";
            case 1, 2, 3 -> "D5DBDB";
            default -> "FFFFFF";
        };
        return Color.decode("#" + colorCode);
    }
}
