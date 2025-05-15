package component;

import com.formdev.flatlaf.FlatClientProperties;
import app.MessageType;
import event.PublicEvent;
import model.Model_Send_Message;
import model.Model_User_Account;
import service.Service;
import swing.JIMSendTextPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Chat_Bottom extends javax.swing.JPanel {
    public Model_User_Account getUser() {
        return user;
    }

    public void setUser(Model_User_Account user) {
        this.user = user;
        panelMore.setUser(user);
    }

    private Model_User_Account user;

    public Chat_Bottom() {
        initComponents();
        init();
    }

    private void init() {
        mig = new MigLayout("fill", "0[]0[fill,grow]0[]0", "0[fill]0[]0");
        setLayout(mig);
        setBackground(new Color(31, 31, 31));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        scroll.setBackground(new Color(58, 58, 58));
        scroll.getViewport().setBackground(new Color(58, 58, 58));
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.setBackground(new Color(58, 58, 58));
        txt.setForeground(Color.WHITE);
        txt.setCaretColor(new Color(63, 167, 250));
        txt.setSelectionColor(new Color(63, 167, 250));
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
                if (ke.getKeyChar() == 10 && ke.isControlDown()) {
                    eventSend(txt);
                }
            }
        });
        txt.setBorder(new EmptyBorder(5, 20, 5, 5));
        txt.setHintText(" Aa");
        scroll.setViewportView(txt);
        JScrollBar sb = new JScrollBar();
        sb.setBackground(new Color(58, 58, 58));
        sb.putClientProperty(FlatClientProperties.STYLE, "" + "width:2;" + "thumbInsets:0,0,0,0;" + "track:#E5E5E5;");
        sb.setUnitIncrement(10);
        scroll.setVerticalScrollBar(sb);
        add(sb, "h 0:10:");
        add(scroll, "w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]5[]20", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(new Color(58, 58, 58));
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/images/icon-send.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                eventSend(txt);
            }
        });
        JButton cmdMore = new JButton();
        cmdMore.setBorder(null);
        cmdMore.setContentAreaFilled(false);
        cmdMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/images/more_disable.png")));
        cmdMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelMore.isVisible()) {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/images/more_disable.png")));
                    panelMore.setVisible(false);
                    mig.setComponentConstraints(panelMore, "dock south,h 0!");
                    revalidate();
                } else {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/images/more.png")));
                    panelMore.setVisible(true);
                    mig.setComponentConstraints(panelMore, "dock south,h 170!");
                    revalidate();
                }
            }
        });
        panel.add(cmdMore);
        panel.add(cmd);
        add(panel, "wrap");
        panelMore = new Panel_More();
        panelMore.setVisible(false);
        add(panelMore, "dock south,h 0!");
    }

    private void eventSend(JIMSendTextPane txt) {
        String text = txt.getText().trim();
        if (!text.equals("")) {
            Model_Send_Message message = new Model_Send_Message(MessageType.TEXT,
                    Service.getInstance().getUser().getUserID(), user.getUserID(), text);
            send(message);
            PublicEvent.getInstance().getEventChat().sendMessage(message);
            txt.setText("");
            txt.grabFocus();
            refresh();
        } else {
            txt.grabFocus();
        }
    }

    private void send(Model_Send_Message data) {
        Service.getInstance().getClient().emit("send_to_user", data.toJsonObject());
    }

    private void refresh() {
        revalidate();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setBackground(new java.awt.Color(31, 31, 31));
        setForeground(new java.awt.Color(255, 255, 255));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 40, Short.MAX_VALUE));
    }

    private MigLayout mig;
    private Panel_More panelMore;
}
