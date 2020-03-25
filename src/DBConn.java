import java.sql.*;
//Databasklass hanterar kopplingar mellan programmet och mysql databas
public class DBConn {

    private Connection conn = null;
    private static DBConn db = null;
    private String host = "127.0.0.1";
    private String port = "3306";
    private String database = "memberbase";
    private String username = "root";
    private String password = "11851185";

    private DBConn(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);

        }catch(ClassNotFoundException cnfe){
            cnfe.printStackTrace();
        }catch(SQLException se){
            se.printStackTrace();
        }
        System.out.println("connection: " + conn + " Successfull");
    }
    public static DBConn getInstance(){
        if(db==null){
            db = new DBConn();
        }
        return db;
    }
    public Connection getConnection(){
        return conn;
    }

}
