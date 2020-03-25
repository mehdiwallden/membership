import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

//Fungerar på exakt samma sätt som GUI-klassen, fast den här visar medlemsinfo-sidan, där man kan ändra på information

public class UserGui {

    private JFrame frame;
    private JPanel panel;

    private JLabel label_pnr, label_name, label_mobile, user_pnr;
    private JTextField user_name, user_mobile;

    private JButton b_logout = new JButton("Logga ut");
    private JButton b_update = new JButton("Uppdatera");

    private User u;


    UserGui(User u){

        this.u = u;

        frame = new JFrame("Välkommen " + u.getName());
        panel = new JPanel();

        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        this.createComponents();

    }

    private void addListeners(){

        LogoutHandler lh = new LogoutHandler();
        UpdateHandler uh = new UpdateHandler();

        b_logout.addActionListener(lh);
        b_update.addActionListener(uh);


    }

    private void createComponents(){

        label_pnr = new JLabel("Personnummer: ");
        label_name = new JLabel("Namn: ");
        label_mobile = new JLabel("Mobiltelefon: ");

        user_pnr = new JLabel(u.getPnr());
        user_name = new JTextField(12);
        user_mobile = new JTextField(12);

        user_name.setText(u.getName());
        user_mobile.setText(u.getMobile());

        this.addComponents();
    }

    private void addComponents(){

        panel.setLayout(new GridLayout(4,2));

        panel.add(label_pnr);
        panel.add(user_pnr);

        panel.add(label_name);
        panel.add(user_name);

        panel.add(label_mobile);
        panel.add(user_mobile);

        panel.add(b_logout);
        panel.add(b_update);

        frame.pack();

        this.addListeners();
    }

    private class LogoutHandler implements ActionListener{
        public void actionPerformed(ActionEvent ae){

            frame.dispose();
        }
    }

    private class UpdateHandler implements ActionListener{
        public void actionPerformed(ActionEvent ae){

            try{

                u.updateName(user_name.getText(), true);
                u.updateMobile(user_mobile.getText(), true);
                JOptionPane.showMessageDialog(null, "Din information har uppdaterats!");


            }catch(LengthException le){

                JOptionPane.showMessageDialog(null, le.getMessage());
            }
        }
    }

}
