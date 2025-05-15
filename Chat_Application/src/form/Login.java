package form;

//import formdev.flatlaf.FlatClientProperties;
import event.EventLogin;
import event.EventMessage;
import event.PublicEvent;
import model.Model_Login;
import model.Model_Message;
import model.Model_Register;
import model.Model_User_Account;
import service.Service;
import swing.PanelSlider;
import io.socket.client.Ack;
import swing.RoundedPanel;

public class Login extends javax.swing.JPanel {

        public Login() {
                initComponents();
                init();
        }

        private void init() {
                P_Login login = new P_Login();
                P_Register register = new P_Register();
                slide.showSlid(login, PanelSlider.SliderType.NONE);

                PublicEvent.getInstance().addEventLogin(new EventLogin() {

                        @Override
                        public void login(Model_Login data) {
                                new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                                PublicEvent.getInstance().getEventMain().showLoading(true);
                                                Service.getInstance().getClient().emit("login", data.toJsonObject(),
                                                                new Ack() {
                                                                        @Override
                                                                        public void call(Object... os) {
                                                                                if (os.length > 0) {
                                                                                        boolean action = (Boolean) os[0];
                                                                                        if (action) {
                                                                                                Service.getInstance()
                                                                                                                .setUser(new Model_User_Account(
                                                                                                                                os[1]));
                                                                                                PublicEvent.getInstance()
                                                                                                                .getEventMain()
                                                                                                                .showLoading(false);
                                                                                                PublicEvent.getInstance()
                                                                                                                .getEventMain()
                                                                                                                .initChat();
                                                                                        } else {
                                                                                                // password wrong
                                                                                                PublicEvent.getInstance()
                                                                                                                .getEventMain()
                                                                                                                .showLoading(false);
                                                                                        }
                                                                                } else {
                                                                                        PublicEvent.getInstance()
                                                                                                        .getEventMain()
                                                                                                        .showLoading(false);
                                                                                }
                                                                        }
                                                                });

                                        }
                                }).start();
                        }

                        @Override
                        public void register(Model_Register data, EventMessage message) {
                                Service.getInstance().getClient().emit("register", data.toJsonObject(), new Ack() {
                                        @Override
                                        public void call(Object... os) {
                                                if (os.length > 0) {
                                                        Model_Message ms = new Model_Message((boolean) os[0],
                                                                        os[1].toString());
                                                        if (ms.isAction()) {
                                                                Model_User_Account user = new Model_User_Account(os[2]);
                                                                Service.getInstance().setUser(user);
                                                        }
                                                        message.callMessage(ms);
                                                        // call message back when done register
                                                }
                                        }
                                });
                        }

                        @Override
                        public void goRegister() {
                                slide.showSlid(register, PanelSlider.SliderType.RIGHT_TO_LEFT);
                        }

                        @Override
                        public void goLogin() {
                                slide.showSlid(login, PanelSlider.SliderType.LEFT_TO_RIGHT);
                        }
                });
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                pic = new swing.PictureBox();
                jPanel1 = new RoundedPanel(30);
                jPanel2 = new RoundedPanel(30);
                slide = new swing.PanelSlider();

                setBackground(new java.awt.Color(46, 46, 46));

                pic.setImage(new javax.swing.ImageIcon(getClass().getResource("/images/icon-transparent.png"))); // NOI18N

                javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
                pic.setLayout(picLayout);
                picLayout.setHorizontalGroup(
                                picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 538, Short.MAX_VALUE));
                picLayout.setVerticalGroup(
                                picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGap(0, 242, Short.MAX_VALUE));

                jPanel1.setBackground(new java.awt.Color(31, 31, 31));

                jPanel2.setBackground(new java.awt.Color(31, 31, 31));

                slide.setBackground(new java.awt.Color(31, 31, 31));

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(slide,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout
                                                                .createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(slide,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addContainerGap()));

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(1, 1, 1)));
                jPanel1Layout.setVerticalGroup(
                                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGap(1, 1, 1)
                                                                .addComponent(jPanel2,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addGap(1, 1, 1)));

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(95, 95, 95)
                                                                .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jPanel1,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(200, 200, 200)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(pic,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addGap(0, 100, Short.MAX_VALUE)
                                                                                                                .addComponent(jPanel1,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGap(0, 100, Short.MAX_VALUE)))
                                                                .addGap(9, 9, 9)));
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JPanel jPanel1;
        private javax.swing.JPanel jPanel2;
        private swing.PictureBox pic;
        private swing.PanelSlider slide;
        // End of variables declaration//GEN-END:variables
}
