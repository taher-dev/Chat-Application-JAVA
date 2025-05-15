package component;

import com.formdev.flatlaf.FlatClientProperties;
import event.PublicEvent;
import model.Model_User_Account;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Item_People extends swing.RoundedPanel {


        public Model_User_Account getUser() {
                return user;
        }

        private boolean mouseOver;
        private final Model_User_Account user;

        public Item_People(Model_User_Account user) {
                this.user = user;
                initComponents();
                lb.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:+1;");
                lb.setText(user.getUserName());
                lbStatus.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:-1 italic;");
                activeStatus.setActive(user.isStatus());
                init();
        }

        public void updateStatus() {
                activeStatus.setActive(user.isStatus());
        }

        private void init() {
                addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent me) {
                                setBackground(new Color(58, 58, 58));
                                mouseOver = true;
                        }

                        @Override
                        public void mouseExited(MouseEvent me) {
                                setBackground(new Color(31, 31, 31));
                                mouseOver = false;
                        }

                        @Override
                        public void mouseReleased(MouseEvent me) {
                                if (mouseOver) {
                                        PublicEvent.getInstance().getEventMain().selectUser(user);
                                }
                        }
                });
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new swing.ImageAvatar();
        lb = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        activeStatus = new swing.ActiveStatus();

        setBackground(new java.awt.Color(31, 31, 31));

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N

        javax.swing.GroupLayout imageAvatar1Layout = new javax.swing.GroupLayout(imageAvatar1);
        imageAvatar1.setLayout(imageAvatar1Layout);
        imageAvatar1Layout.setHorizontalGroup(
            imageAvatar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        imageAvatar1Layout.setVerticalGroup(
            imageAvatar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        lb.setForeground(new java.awt.Color(255, 255, 255));
        lb.setText("Name");

        lbStatus.setForeground(new java.awt.Color(117, 117, 117));
        lbStatus.setText("New User");

        activeStatus.setActive(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lb, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbStatus)
                        .addGap(3, 3, 3)
                        .addComponent(activeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(activeStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ActiveStatus activeStatus;
    private swing.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
