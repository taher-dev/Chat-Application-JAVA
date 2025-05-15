package component;

import com.formdev.flatlaf.FlatClientProperties;
import model.Model_User_Account;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

public class Chat_Title extends JPanel {
    public Model_User_Account getUser() {
        return user;
    }

    private Model_User_Account user;

    public Chat_Title() {
        initComponents();
        setOpaque(false);
        lbName.putClientProperty(FlatClientProperties.STYLE, "" + "font:+1 bold;");
    }

    public void setUserName(Model_User_Account user) {
        this.user = user;
        lbName.setText(user.getUserName());
        if (user.isStatus()) {
            statusActive();
        } else {
            setStatusText("Offline");
        }
    }

    public void updateUser(Model_User_Account user) {
        if (this.user == user) {
            lbName.setText(user.getUserName());
            if (user.isStatus()) {
                statusActive();
            } else {
                setStatusText("Offline");
            }
        }
    }

    private void statusActive() {
        lbStatus.setText("Active now");
        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
    }

    private void setStatusText(String text) {
        lbStatus.setText(text);
        lbStatus.setForeground(new Color(160, 160, 160));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        Shape topRounded = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight() + 20, 20, 20);
        g2.fill(topRounded);
        g2.dispose();
        super.paintComponent(g);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        setBackground(new java.awt.Color(31, 31, 31));
        layer.setLayout(new java.awt.GridLayout(0, 1));
        lbName.setFont(new java.awt.Font("Segoe UI", 1, 12));
        lbName.setForeground(new java.awt.Color(255, 255, 255));
        lbName.setText("Name");
        layer.add(lbName);
        lbStatus.setForeground(new java.awt.Color(49, 162, 76));
        lbStatus.setText("Active now");
        layer.add(lbStatus);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(20, 20, 20)
                        .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(398, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addGap(3, 3, 3)
                        .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                        .addGap(3, 3, 3)));
    }

    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
}
