
import java.sql.*;
import java.util.*;

/**
 *
 * @author
 */
public class DbaseProject {
    
    //global variables
    public static String url = "jdbc:mysql://localhost/hotel";
    public static String user = "root";
    public static String pass = "";
    public static int choice = 0;

    public static void main(String[] args) {

        try {
            Scanner kbd = new Scanner(System.in);
            DbaseProject db = new DbaseProject();
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.print(" 1.) View accomodation \n 2.) Login User \n 3.) Register User \n Enter the number of your choice: ");
            choice = kbd.nextInt();
            switch (choice) {
                case 1:
                    db.viewAcc(conn);
                    break;
                case 2:
                    db.login(conn);
                    break;
                case 3:
                    db.register();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAcc(Connection conn) throws SQLException {
        Statement myStatement = conn.createStatement();

        String query = "select * from rooms";//View query here.
        ResultSet res = myStatement.executeQuery(query);

        while (res.next()) {
            int room_id = res.getInt(1);
            String Accomodation = res.getString(2);
            String Availability = res.getString(3);
            double price_per_night = res.getDouble(4);
//            byte room_img = res.getByte(5);
            System.out.println(room_id + " - " + Accomodation + " - " + Availability + " - " + price_per_night);//Format for output
        }
    }

    public void login(Connection conn) throws SQLException {
        Statement state = conn.createStatement();
        Scanner kbd = new Scanner(System.in);
        try {
            System.out.print("email: ");
            String email = kbd.next();
            System.out.print("password: ");
            String password = kbd.next();
            ResultSet emailSet = state.executeQuery("SELECT email , password FROM client WHERE email = '" + email + "' AND password = '" + password + "'");
            if (emailSet.next()) {
                System.out.println("Successfully logged in!!!");
            } else {
                System.out.println("Incorrect credentials!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register() throws SQLException {
        Scanner kbd = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);

            Statement state = conn.createStatement();

            String fname, lname, phoneno, email, password;
            System.out.println("Register your account here");
            System.out.print("First name: ");
            fname = kbd.nextLine();
            System.out.print("Last name: ");
            lname = kbd.nextLine();
            System.out.print("Phone number: ");
            phoneno = kbd.nextLine();
            System.out.print("Email: ");
            email = kbd.nextLine();
            System.out.print("Password: ");
            password = kbd.nextLine();

            String query = "insert into client(fname, lname, phoneno, email, password) values('" + fname + "','" + lname + "' ,'" + phoneno + "' ,'" + email + "' ,'" + password + "' )"; //View query here.
            state.executeUpdate(query);

            System.out.println("Your account has successfully added! ");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
