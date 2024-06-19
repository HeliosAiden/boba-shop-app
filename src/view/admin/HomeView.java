package view.admin;

import utils.RandomColor;


public class HomeView extends javax.swing.JPanel {

    private boolean isShowLed = false;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labLogo;

    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }

    public HomeView() {
        initComponents();

    }

    // TODOL: Refactor initComponents()
    private void initComponents() {

        labLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 255, 255));
        setMaximumSize(new java.awt.Dimension(1008, 680));
        setMinimumSize(new java.awt.Dimension(1008, 680));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        setLayout(null);

        labLogo.setIcon(new javax.swing.ImageIcon("/assets/dbimgs/dbmilk_tea_logo.png"));
        labLogo.setPreferredSize(new java.awt.Dimension(400, 400));
        add(labLogo);
        labLogo.setBounds(780, 10, 200, 190);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel1.setIcon(new javax.swing.ImageIcon("assets/imgs/home-background.png"));
        jLabel1.setToolTipText("");
        jLabel1.setMaximumSize(new java.awt.Dimension(10080000, 68000000));
        add(jLabel1);
        jLabel1.setBounds(0, 0, 1008, 680);
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        isShowLed = !isShowLed;
        makeLed();
    }//GEN-LAST:event_formMousePressed

    private void makeLed() {
        if (!isShowLed) {
            return;
        }
        setBackground(RandomColor.getColor());
        jLabel1.setForeground(RandomColor.getContrastColor(getBackground()));
        setTimeout(this::makeLed, 200);
    }

}
