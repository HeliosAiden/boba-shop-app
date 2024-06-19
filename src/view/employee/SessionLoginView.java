package view.employee;

import java.awt.Dimension;
import javax.swing.JLabel;


public class SessionLoginView extends javax.swing.JPanel {

    private javax.swing.JLabel lbEndTime;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lbStartTime;

    public SessionLoginView() {
        setPreferredSize(new Dimension(400, 60));
        initComponents();
    }

    public JLabel getLbEndTime() {
        return lbEndTime;
    }

    public JLabel getLbMessage() {
        return lbMessage;
    }

    public JLabel getLbStartTime() {
        return lbStartTime;
    }


    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lbStartTime = new javax.swing.JLabel();
        lbEndTime = new javax.swing.JLabel();
        lbMessage = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(400, 60));
        setMinimumSize(new java.awt.Dimension(400, 60));
        setLayout(new java.awt.GridBagLayout());

        lbStartTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbStartTime.setText("12/01/2021 19:12:02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lbStartTime, gridBagConstraints);

        lbEndTime.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbEndTime.setText("12/01/2021 19:12:02");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lbEndTime, gridBagConstraints);

        lbMessage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(82, 164, 0));
        lbMessage.setText("Đang hoạt động");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lbMessage, gridBagConstraints);
    }
}
