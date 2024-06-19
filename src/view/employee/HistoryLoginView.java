package view.employee;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class HistoryLoginView extends javax.swing.JFrame {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelContent1;

    public HistoryLoginView() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // TODO: refactor initComponents()
    private void initComponents() {

        panelContent1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lịch sử đăng nhập");
        setMaximumSize(new java.awt.Dimension(410, 600));
        setMinimumSize(new java.awt.Dimension(410, 300));
        setPreferredSize(new java.awt.Dimension(410, 300));
        getContentPane().setLayout(new java.awt.CardLayout());

        panelContent1.setMaximumSize(new java.awt.Dimension(403, 298));
        panelContent1.setMinimumSize(new java.awt.Dimension(403, 298));
        getContentPane().add(panelContent1, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JPanel getPanelContent1() {
        return panelContent1;
    }

}
