import java.sql.*;
import java.util.*;

/**
 *
 * @author d524lab
 */
public class Login {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel","root","");
            Statement state = conn.createStatement();
            ResultSet res = state.executeQuery("SELECT * FROM client");
            while (res.next()){
                System.out.println(res.getString("password") + " " + res.getString("email"));
            } 
            
            Scanner kbd = new Scanner(System.in);
            System.out.print("email: ");
            String email = kbd.next();
            System.out.print("\npassword: ");
            String password = kbd.next();
            ResultSet emailSet = state.executeQuery("SELECT email , password FROM client WHERE email = '" + email + "' AND password = '" + password + "'");
            if (emailSet.next()) {
                System.out.println(emailSet.getString("email"));
                System.out.println(emailSet.getString("password"));
            } else {
                System.out.println("may problema");
            }
         
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    
}
