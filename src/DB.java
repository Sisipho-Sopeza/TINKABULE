import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("");
            con= DriverManager.getConnection("","","");
        }catch(Exception e){System.out.println(e);}
        return con;
    }

}
