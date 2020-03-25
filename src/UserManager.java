import java.sql.*;
import java.util.*;

public class UserManager {

    HashMap users; //associativ array

    public UserManager(){

        if(users==null){

            //om inte hashmap users är instansierad, instansiera en ny och förser den med alla användare som är registrerade i databasen
            users = new HashMap();
            Connection conn = DBConn.getInstance().getConnection();
            String sql = "SELECT * FROM users";

            try{

                Statement stm = conn.createStatement();
                ResultSet result = stm.executeQuery(sql);

                while(result.next()){

                    String pnr = result.getString("pnr");
                    String name = result.getString("name");
                    String mobile = result.getString("mobile");
                    String password = result.getString("password");

                    User member = new User(pnr, name, mobile, password, false);
                    this.addUser(member);

                    System.out.println("User added!");

                }

            }catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public void addUser(User a){ //a står för user objekt
        //key är personnummer, och value är ett user-objekt
        users.put(a.getPnr(), a);

    }

    public User findUser(String pnr){
        return (User)users.get(pnr); //(User) casting till ett User objekt
    }

    public  boolean authenticateUser(User u, String password){

        String userPassword = u.getPassword();

        if(userPassword.equals(password)){

            System.out.println("True Password");
            return true;
        }else{

            System.out.println("False Password");
            return false;
        }
    }
}
