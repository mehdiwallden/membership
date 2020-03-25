
import java.sql.*;

public class User {

    private String pnr;
    private String name;
    private String mobile;
    private String password;

    public User(String pnr, String name, String mobile,String password, boolean toDB){

        this.pnr = pnr;
        this.name = name;
        this.mobile = mobile;
        this.password = password;

        if(toDB == true){

            Connection conn = DBConn.getInstance().getConnection();
            try{
                Statement statement = conn.createStatement();
                String sql = "INSERT INTO users (pnr, name, mobile, password) VALUES('"+ this.pnr +"','" + this.name + "','" + this.mobile + "','"+ this.password +"')";
                statement.executeUpdate(sql);
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }

    public String getPnr(){
        return pnr;
    }

    public String getMobile(){
        return mobile;
    }

    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }

    public void updateName(String name, boolean toDB) throws LengthException{

        if(name.length()<51){

            this.name = name;
            String sql = "UPDATE users SET name ='" + this.name + "' WHERE pnr ='" + this.pnr + "'";
            Connection conn = null;
            try{
                conn = DBConn.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                int rows = stmt.executeUpdate(sql);

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            throw new LengthException("Namnet får inte överstiga 50 tecken");
        }

    }

    public void updateMobile(String mobile, boolean toDB) throws LengthException{

        if(mobile.length()<11){
            this.mobile = mobile;

            String sql = "UPDATE users SET mobile ='" + this.mobile + "' WHERE pnr ='" + this.pnr + "'";
            Connection conn = null;
            try{
                conn = DBConn.getInstance().getConnection();
                Statement stmt = conn.createStatement();
                int rows = stmt.executeUpdate(sql);

            }catch(Exception e){
                e.printStackTrace();
            }

        }else{
            throw new LengthException("Mobilnumret får inte överstiga 10 siffror");
        }
    }
}
