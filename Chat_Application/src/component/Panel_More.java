package component;

import com.formdev.flatlaf.FlatClientProperties;
import app.MessageType;
import emoji.Emogi;
import emoji.Model_Emoji;
import event.PublicEvent;
import main.Main;
import model.Model_Send_Message;
import model.Model_User_Account;
import service.Service;
import swing.WrapLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import net.miginfocom.swing.MigLayout;

public class Panel_More extends javax.swing.JPanel {
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
    }

    private Model_User_Account user;

    public Panel_More() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx"));
        setBackground(new java.awt.Color(31, 31, 31));
        panelHeader = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setOpaque(false);
            }
        };
        panelHeader.setOpaque(false);
        panelHeader.setBackground(new java.awt.Color(31, 31, 31));
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getButtonImage());
        panelHeader.add(getButtonFile());
        panelHeader.add(getEmojiStyle1());
        panelHeader.add(getEmojiStyle2());
        add(panelHeader, "w 100%, h 30!, wrap");
        panelDetail = new JPanel();
        panelDetail.setBackground(new java.awt.Color(31, 31, 31));
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBackground(new java.awt.Color(31, 31, 31));
        ch.getViewport().setBackground(new java.awt.Color(31, 31, 31));
        ch.setBorder(BorderFactory.createEmptyBorder());
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE,
                "" + "width:5;" + "background:null;" + "thumbInsets:0,0,0,0;");
        ch.getVerticalScrollBar().setUnitIncrement(10);
        add(ch, "w 100%, h 100%");
        showDefaultStyleEmoji();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int arc = 20;
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, arc);
        g2.fillRect(0, 0, getWidth(), getHeight() - arc);
        g2.dispose();
        super.paintComponent(g);
    }

    private JButton getButtonImage() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/images/image.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(true);
                ch.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return file.isDirectory() || isImageFile(file);
                    }

                    @Override
                    public String getDescription() {
                        return "Image File";
                    }
                });
                int option = ch.showOpenDialog(Main.getFrames()[0]);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File files[] = ch.getSelectedFiles();
                    try {
                        for (File file : files) {
                            Model_Send_Message message = new Model_Send_Message(MessageType.IMAGE,
                                    Service.getInstance().getUser().getUserID(), user.getUserID(), "");
                            Service.getInstance().addFile(file, message);
                            PublicEvent.getInstance().getEventChat().sendMessage(message);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return cmd;
    }

    private JButton getButtonFile() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/images/link.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.showOpenDialog(Main.getFrames()[0]);
            }
        });
        return cmd;
    }

    private JButton getEmojiStyle1() {
        cmdDefaultEmoji = new OptionButton();
        cmdDefaultEmoji.setIcon(Emogi.getInstance().getImoji(1).toSize(25, 25).getIcon());
        cmdDefaultEmoji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showDefaultStyleEmoji();
            }
        });
        return cmdDefaultEmoji;
    }

    private JButton getEmojiStyle2() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(Emogi.getInstance().getImoji(21).toSize(25, 25).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for (Model_Emoji d : Emogi.getInstance().getStyle2()) {
                    panelDetail.add(getButton(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return cmd;
    }

    private void showDefaultStyleEmoji() {
        clearSelected();
        cmdDefaultEmoji.setSelected(true);
        panelDetail.removeAll();
        for (Model_Emoji d : Emogi.getInstance().getStyle1()) {
            panelDetail.add(getButton(d));
        }
        panelDetail.repaint();
        panelDetail.revalidate();
    }

    private JButton getButton(Model_Emoji data) {
        JButton cmd = new JButton(data.getIcon());
        cmd.setName(data.getId() + "");
        cmd.setBorder(new EmptyBorder(3, 3, 3, 3));
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setContentAreaFilled(false);
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Model_Send_Message message = new Model_Send_Message(MessageType.EMOJI,
                        Service.getInstance().getUser().getUserID(), user.getUserID(), data.getId() + "");
                sendMessage(message);
                PublicEvent.getInstance().getEventChat().sendMessage(message);
            }
        });
        return cmd;
    }

    private void sendMessage(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setBackground(new java.awt.Color(31, 31, 31));
        setForeground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 515, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 84, Short.MAX_VALUE));
    }

    private void clearSelected() {
        for (Component c : panelHeader.getComponents()) {
            if (c instanceof OptionButton) {
                ((OptionButton) c).setSelected(false);
            }
        }
    }

    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    private JPanel panelHeader;
    private JPanel panelDetail;
    private OptionButton cmdDefaultEmoji;
}
