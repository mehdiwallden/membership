import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

//GUI (Graphical User Interface) hanterar själva fronten av programmet, alltså textfält, knappar och labels. Gör den användarvänlig

public class Gui {
    //instansiera fönster, labels, textfält, lösenordsfält och knappar som vi sedan kan använda oss av
    private JFrame frame = new JFrame("Användarregister");
    private JPanel panel = new JPanel();

    private JLabel label_pnr, label_name, label_username, label_mobile, label_password;
    private JTextField tf_pnr, tf_name, tf_mobile;
    private JPasswordField pf_password;

    private JButton b_register , b_login;

    //extra för menyn på toppen som uppfyller samma funtionen som logga in/ registerar
    private JMenuBar bar = new JMenuBar();
    private JMenu menu = new JMenu("File");
    private JMenuItem registerItem = new JMenuItem("Registrera");
    private JMenuItem loginItem = new JMenuItem("Logga in");


    private UserManager um;

    public Gui(UserManager um){ //um står för Usermanager

        this.um = um;

        this.createComponents();

        frame.setContentPane(panel);
        frame.setJMenuBar(bar);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


    }

    private void addListeners(){

        LoginHandler lh = new LoginHandler();
        RegisterHandler rh = new RegisterHandler();

        b_login.addActionListener(lh);
        b_register.addActionListener(rh);

        loginItem.addActionListener(lh);
        registerItem.addActionListener(rh);
       /*
        b_find.addActionListener(fh);
        findItem.addActionListener(fh);

        b_print.addActionListener(ph);
        printItem.addActionListener(ph);

        b_clear.addActionListener(ch);
        clearItem.addActionListener(ch);
        */
    }

    private void createComponents(){

        label_pnr = new JLabel("Personnummer:");
        label_name = new JLabel("Namn:");
        label_mobile = new JLabel("Mobilnummer");
        label_password = new JLabel("Lösenord:");

        tf_pnr = new JTextField(12);
        tf_name = new JTextField(12);
        tf_mobile = new JTextField(12);
        pf_password = new JPasswordField(12);

        b_register = new JButton("Registrera");
        b_login = new JButton("Logga in");
        /*
        b_find = new JButton("Find");
        b_print = new JButton("Print");
        b_clear = new JButton("Clear");
        */
        this.addComponents();

    }

    private void addComponents(){

        panel.setLayout(new GridLayout(5,2));

        panel.add(label_pnr);
        panel.add(tf_pnr);

        panel.add(label_name);
        panel.add(tf_name);

        panel.add(label_mobile);
        panel.add(tf_mobile);

        panel.add(label_password);
        panel.add(pf_password);

        panel.add(b_login);
        panel.add(b_register);

        bar.add(menu);

        menu.add(loginItem);
        menu.add(registerItem);

        this.addListeners();

    }

    private class LoginHandler implements ActionListener{
        public void actionPerformed(ActionEvent ae){

            LoginGui loginGui = new LoginGui();

        }
    }

    private class RegisterHandler implements ActionListener{
        public void actionPerformed(ActionEvent ae){

            User u = new User(tf_pnr.getText(), tf_name.getText(), tf_mobile.getText(), pf_password.getText(), true);

            um.addUser(u);

            tf_pnr.setText("");
            tf_name.setText("");
            tf_mobile.setText("");
            pf_password.setText("");

            JOptionPane.showMessageDialog(null, "Du har registrerats!");
        }
    }
}
