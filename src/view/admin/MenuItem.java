package view.admin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.Icon;
import utils.IconManager;
import utils.SidebarColor;


public class MenuItem extends javax.swing.JPanel {

    private ArrayList<MenuItem> subMenu = new ArrayList<>();
    private MenuItem parentMenu = null;
    private String id;
    private int level;
    public boolean active; // Biến kiểm tra xem có đang chọn không
    IconManager im = new IconManager();
    Color inactiveChildColor = Color.decode("#D5DBDB");
    Color inactiveParrentColor = Color.decode("#D5DBDB");
    Color activeParrentColor = Color.decode("#F5CBA7");
    Color activeChildColor = Color.decode("#EAFAF1");
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbMenuName;
    private javax.swing.JLabel lbOpen;

    public MenuItem(String id, Icon icon, String menuName, MenuItem... subMenu) {
        initComponents();
        this.id = id;
        lbIcon.setIcon(icon);
        lbMenuName.setText(menuName);
    }

    public ArrayList<MenuItem> getSubMenu() {
        return subMenu;
    }

    public void addSubMenu(MenuItem item) {
        item.setParentMenu(this);
        item.setLevel(this.level + 1);
        this.subMenu.add(item);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuItem getParentMenu() {
        return parentMenu;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setParentMenu(MenuItem parentMenu) {
        this.parentMenu = parentMenu;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
        if (active) {
            setBackground(SidebarColor.getActiveColor(level));
            if (hasSubMenu()) {
                lbOpen.setIcon(im.getIcon("opened_menu_25px.png"));
            }
        } else {
            setBackground(SidebarColor.getInactiveColor(level));
            if (hasSubMenu()) {
                lbOpen.setIcon(im.getIcon("closed_menu_25px.png"));
            }
        }
        if (!hasSubMenu()) {
            lbOpen.setVisible(false);
            this.updateUI();
        }
//        if (!active) {
//            if (hasSubMenu() || getParentMenu() == null) {
//                setBackground(inactiveParrentColor);
//            } else {
//                setBackground(inactiveChildColor);
//            }
//        } else if (hasSubMenu() || getParentMenu() == null) {
//            setBackground(activeParrentColor);
//        } else {
//            setBackground(activeChildColor);
//        }
    }

    //Kiểm tra xem có menu con không
    public boolean hasSubMenu() {
        return !subMenu.isEmpty();
    }

    public boolean equals(MenuItem obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return true;
//        }
//        return obj.getId() == this.getId();
        return this == obj;
    }

    public boolean hasChild(MenuItem obj) {
        if (obj == null) {
            return false;
        }
        if (obj.equals(this)) {
            return true;
        }
        return hasChild(obj.getParentMenu());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "MenuItem{" + "id=" + id + ", active=" + active + '}';
    }

    // TODO: Refactor initComponents()
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbIcon = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbOpen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbMenuName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setPreferredSize(new java.awt.Dimension(200, 45));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setMaximumSize(new java.awt.Dimension(45, 45));
        jPanel1.setMinimumSize(new java.awt.Dimension(45, 45));
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(45, 45));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel3.setMaximumSize(new java.awt.Dimension(45, 45));
        jPanel3.setMinimumSize(new java.awt.Dimension(45, 45));
        jPanel3.setOpaque(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        add(jPanel3, java.awt.BorderLayout.EAST);

        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(155, 45));

        lbMenuName.setFont(new java.awt.Font("Segoe UI", 0, 14));
        lbMenuName.setText("Menu Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(lbMenuName)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lbMenuName)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }

}
