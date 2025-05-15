package form;

import com.formdev.flatlaf.FlatClientProperties;
import component.Item_People;
import event.EventMenuLeft;
import event.PublicEvent;
import model.Model_User_Account;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import swing.RoundedPanel;

public class Menu_Left extends javax.swing.JPanel {
    private List<Model_User_Account> userAccount;

    public Menu_Left() {
        initComponents();
        init();
    }

    private void init() {
        addHoverEffect(menuMessage);
        addHoverEffect(menuGroup);
        sp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sp.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
                "" + "width:5;" + "background:null;" + "trackArc:$ScrollBar.thumbArc;" + "thumbInsets:0,0,0,0;");
        sp.getVerticalScrollBar().setUnitIncrement(10);
        menuList.setLayout(new MigLayout("fillx", "0[fill]0", "0[]0"));
        userAccount = new ArrayList<>();
        PublicEvent.getInstance().addEventMenuLeft(new EventMenuLeft() {
            @Override
            public void newUser(List<Model_User_Account> users) {
                for (Model_User_Account d : users) {
                    userAccount.add(d);
                    menuList.add(new Item_People(d), "wrap");
                    refreshMenuList();
                }
            }

            @Override
            public void userConnect(int userID) {
                for (Model_User_Account u : userAccount) {
                    if (u.getUserID() == userID) {
                        u.setStatus(true);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if (menuMessage.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        Item_People item = (Item_People) com;
                        if (item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }

            @Override
            public void userDisconnect(int userID) {
                for (Model_User_Account u : userAccount) {
                    if (u.getUserID() == userID) {
                        u.setStatus(false);
                        PublicEvent.getInstance().getEventMain().updateUser(u);
                        break;
                    }
                }
                if (menuMessage.isSelected()) {
                    for (Component com : menuList.getComponents()) {
                        Item_People item = (Item_People) com;
                        if (item.getUser().getUserID() == userID) {
                            item.updateStatus();
                            break;
                        }
                    }
                }
            }
        });
        showMessage();
        scrollWrapper.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollWrapper.setOpaque(false);
        menu.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        menu.setOpaque(false);
    }

    private void showMessage() {
        menuList.removeAll();
        for (Model_User_Account d : userAccount) {
            menuList.add(new Item_People(d), "wrap");
        }
        refreshMenuList();
    }

    private void showGroup() {
        menuList.removeAll();
        refreshMenuList();
    }

    private void showBox() {
        menuList.removeAll();
        refreshMenuList();
    }

    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }

    private void addHoverEffect(JComponent comp) {
        comp.setOpaque(false);
        comp.setBackground(new java.awt.Color(0, 0, 0, 0));
        comp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                comp.setBackground(new java.awt.Color(58, 58, 58));
                comp.setOpaque(true);
                comp.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                comp.setBackground(new java.awt.Color(0, 0, 0, 0));
                comp.setOpaque(false);
                comp.repaint();
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        menu = new swing.RoundedPanel(20);
        menuMessage = new component.MenuButton();
        menuGroup = new component.MenuButton();
        jButton1 = new javax.swing.JButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JPanel();
        scrollWrapper = new swing.RoundedPanel(20);
        setBackground(new java.awt.Color(46, 46, 46));
        menu.setBackground(new java.awt.Color(31, 31, 31));
        menu.setOpaque(true);
        menu.setLayout(new javax.swing.BoxLayout(menu, javax.swing.BoxLayout.Y_AXIS));
        menuMessage.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/images/icon-chat-active.png")));
        menuMessage.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/icon-chat-inactive.png")));
        menuMessage.setSelected(true);
        menuMessage.setAlignmentX(CENTER_ALIGNMENT);
        menuMessage.setContentAreaFilled(false);
        menuMessage.setBorderPainted(false);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menuGroup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-group-inactive.png")));
        menuGroup.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/images/icon-group-active.png")));
        menuGroup.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/images/icon-group-inactive.png")));
        menuGroup.setAlignmentX(CENTER_ALIGNMENT);
        menuGroup.setContentAreaFilled(false);
        menuGroup.setBorderPainted(false);
        menuGroup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuGroupActionPerformed(evt);
            }
        });
        menu.add(menuMessage);
        menu.add(Box.createVerticalStrut(10));
        menu.add(menuGroup);
        menu.add(Box.createVerticalGlue());
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-logout.png")));
        jButton1.setBackground(new java.awt.Color(31, 31, 31));
        jButton1.setAlignmentX(CENTER_ALIGNMENT);
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        menu.add(jButton1);
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        menuList.setBackground(new java.awt.Color(31, 31, 31));
        menuList.setLayout(new net.miginfocom.swing.MigLayout("fillx, wrap", "[fill]", "[]"));
        sp.setViewportView(menuList);
        scrollWrapper.setBackground(new java.awt.Color(31, 31, 31));
        scrollWrapper.setLayout(new java.awt.BorderLayout());
        scrollWrapper.add(sp, java.awt.BorderLayout.CENTER);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addGap(0, 0, 0)
                        .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 70,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(scrollWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                        .addGap(0, 0, 0)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(scrollWrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                                .addComponent(menu))
                        .addGap(0, 0, 0)));
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        SwingUtilities.getWindowAncestor(this).dispose();
        new main.Main().setVisible(true);
    }

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {
        if (!menuMessage.isSelected()) {
            menuMessage.setSelected(true);
            menuGroup.setSelected(false);
            showMessage();
        }
    }

    private void menuGroupActionPerformed(java.awt.event.ActionEvent evt) {
        if (!menuGroup.isSelected()) {
            menuMessage.setSelected(false);
            menuGroup.setSelected(true);
            showGroup();
        }
    }

    private javax.swing.JButton jButton1;
    private swing.RoundedPanel menu;
    private component.MenuButton menuGroup;
    private javax.swing.JPanel menuList;
    private component.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    private swing.RoundedPanel scrollWrapper;
}
