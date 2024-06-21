package view;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import utils.ErrorPopup;
import view.admin.MenuItem;


public class EmployeeDashboardView extends javax.swing.JFrame {

    JPanel[] cards;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel lbName;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLayout;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelSideBar;
    ArrayList<MenuItem> menuItems = new ArrayList<>();

    public EmployeeDashboardView() {
        initComponents();
        setSize(1208, 680);
        setLocationRelativeTo(null);
        btnLogout.putClientProperty("JButton.buttonType", "roundRect");
    }

    public void showError(String message) {
        ErrorPopup.show(new Exception(message));
    }

    public void showError(Exception e) {
        ErrorPopup.show(e);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }

    public JLabel getLbName() {
        return lbName;
    }

    // Thêm dropdown menu
    public void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            MenuItem item = menu[i];
            menuItems.add(item);
            panelSideBar.add(item);
            ArrayList<MenuItem> subMenus = item.getSubMenu();
            for (MenuItem subMenu : subMenus) {
                addMenu(subMenu);
                subMenu.setVisible(false);
            }
        }
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setCards(JPanel[] cards) {
        this.cards = cards;
        initLayout();
    }

    // Thêm các pane vào cardlayout
    public void initLayout() {
        panelLayout.removeAll();
        for (int i = 0; i < cards.length; i++) {
            panelLayout.add(cards[i]);
        }
        panelLayout.updateUI();
    }

    public JPanel getPanelLayout() {
        return panelLayout;
    }

    public JPanel getPanelSideBar() {
        return panelSideBar;
    }

    public void setPanel(JPanel panel) {
        for (JPanel card : cards) {
            card.setVisible(false);
        }
        panel.setVisible(true);
    }


    // TODO: Refactor initComponents()
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        panelLeft = new javax.swing.JPanel();
        panelHeader = new javax.swing.JPanel();
        lbName = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        panelSideBar = new javax.swing.JPanel();
        panelLayout = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1208, 680));
        setResizable(false);

        panelLeft.setMinimumSize(new java.awt.Dimension(200, 680));
        panelLeft.setPreferredSize(new java.awt.Dimension(200, 680));
        panelLeft.setLayout(new java.awt.BorderLayout());

        panelHeader.setBackground(new java.awt.Color(34, 153, 84));
        panelHeader.setPreferredSize(new java.awt.Dimension(200, 50));
        panelHeader.setLayout(new java.awt.GridBagLayout());

        lbName.setFont(new java.awt.Font("Segoe UI", 1, 14));
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Trần Đức Cường");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        panelHeader.add(lbName, gridBagConstraints);

        btnLogout.setText("Thoát");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.BASELINE;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        panelHeader.add(btnLogout, gridBagConstraints);

        panelLeft.add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelSideBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));
        panelLeft.add(panelSideBar, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelLeft, java.awt.BorderLayout.LINE_START);

        panelLayout.setMaximumSize(new java.awt.Dimension(1008, 680));
        panelLayout.setMinimumSize(new java.awt.Dimension(1008, 680));
        panelLayout.setPreferredSize(new java.awt.Dimension(1008, 680));
        panelLayout.setLayout(new java.awt.CardLayout());
        getContentPane().add(panelLayout, java.awt.BorderLayout.CENTER);

        pack();
    }

}
