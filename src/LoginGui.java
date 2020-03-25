
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
//Samma funktion som GUI, fast denna används till logga in panelen
public class LoginGui {

    private JFrame frame = new JFrame("Logga in");
    private JPanel backgroundPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JPanel southPanel = new JPanel();

    private JLabel label_pnr = new JLabel("Personnummer:");
    private JLabel label_password = new JLabel("Lösenord:");
    private JTextField tf_pnr = new JTextField(12);
    private JPasswordField pf_password = new JPasswordField(12);

    private JButton b_login = new JButton("Logga in");

    private UserManager um = new UserManager();

    public LoginGui(){

        frame.setContentPane(backgroundPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.pack();

        this.addComponents();
    }

    private void addListener(){

        LoginHandler lh = new LoginHandler();

        b_login.addActionListener(lh);

    }

    private void addComponents(){

        backgroundPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new GridLayout(2,2));
        southPanel.setLayout(new GridLayout(1,1));

        backgroundPanel.add(centerPanel, BorderLayout.CENTER);
        backgroundPanel.add(southPanel, BorderLayout.SOUTH);

        centerPanel.add(label_pnr);
        centerPanel.add(tf_pnr);

        centerPanel.add(label_password);
        centerPanel.add(pf_password);

        southPanel.add(b_login);

        frame.pack();

        this.addListener();

    }

    private class LoginHandler implements ActionListener{
        public void actionPerformed(ActionEvent ae){

            User u = um.findUser(tf_pnr.getText());


            if (um.authenticateUser(u, pf_password.getText())){
                frame.dispose();
                UserGui uGui = new UserGui(u);
            }else{
                JOptionPane.showMessageDialog(null, "Fel lösenord. Försök igen!");
            }
        }
    }
}
