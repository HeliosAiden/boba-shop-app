package utils;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class IconManager {

    public static String iconsPath = "assets/icons/";

    public IconManager() {
    }

    public Icon getIcon(String name) {
        return new ImageIcon(getClass().getResource(iconsPath + name));
    }
}
