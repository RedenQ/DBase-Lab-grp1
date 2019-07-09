
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
    public static DbaseProject db = new DbaseProject();

    public static void main(String[] args) {

        try {
            Scanner kbd = new Scanner(System.in);

            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.print(" 1.) Login \n 2.) Register \n 3.) View available accomodations \n 4.) Sort(Cheapest - Most Expensive) \n"
                    + " 5.) Sort(Most Expensive - Cheapest)");
            System.out.println(" ");
            System.out.println(" ");
            System.out.print("Enter the number of your choice: ");
            choice = kbd.nextInt();
            switch (choice) {
                case 1:
                    db.login(conn);
                    break;
                case 2:
                    db.register();
                    break;
                case 3:
                    db.viewAcc(conn);
                    break;
                case 4:
                    db.sortAccAsc();
                    break;
                case 5:
                    db.sortAccDes();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method for viewing available rooms
    public void viewAcc(Connection conn) throws SQLException {
        Statement myStatement = conn.createStatement();
        System.out.println(" ");
        String query = "select * from rooms";//View query here.
        ResultSet res = myStatement.executeQuery(query);

        while (res.next()) {
            int room_id = res.getInt(1);
            String Accomodation = res.getString(2);
            String Availability = res.getString(3);
            double price_per_night = res.getDouble(4);
            //byte room_img = res.getByte(5);
            System.out.println(room_id + " - " + Accomodation + " - " + Availability + " - " + price_per_night);//Format for output
        }
    }

    //method for logging in
    public void login(Connection conn) throws SQLException {
        Statement state = conn.createStatement();
        Scanner kbd = new Scanner(System.in);
        try {
            System.out.println(" ");
            System.out.println("      WELCOME TO LOGIN");
            System.out.println("ENTER YOU EMAIL AND PASSWORD");
            System.out.println(" ");
            System.out.print("email: ");
            String email = kbd.next();
            System.out.print("password: ");
            String password = kbd.next();
            ResultSet emailSet = state.executeQuery("SELECT email , password FROM client WHERE email = '" + email + "' AND password = '" + password + "'");
            if (emailSet.next()) {
                db.viewAcc(conn);
            } else if (!emailSet.next()) {
                int regChoice = 0;
                System.out.println(" ");
                System.out.print(" User not register, do you want to register(yes/no): \n Press (1) if yes, (2) if no: ");
                regChoice = kbd.nextInt();
                switch (regChoice) {
                    case 1:
                        db.register();
                        break;
                    case 2:
                        System.out.println("              BYE!!!!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method for registering account
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

            System.out.print("Do you want to login? Press(1) if yes (2) if no: ");
            choice = kbd.nextInt();
            switch (choice) {
                case 1:
                    db.login(conn);
                    break;
                case 2:
                    System.out.println("BYE");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method to call to sort prices from cheapest to most expensive
    public void sortAccAsc() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement state = conn.createStatement();
        String query = "select room_id, price_per_night from rooms order by price_per_night asc"; //View query here.
        ResultSet res = state.executeQuery(query);

        while (res.next()) {
            int room_id = res.getInt(1);
            double price_per_night = res.getDouble(2);

            System.out.println(room_id + " - " + price_per_night);//Format for output
        }
    }

    //call for sortAccDes()
    public void sortAccDes() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement state = conn.createStatement();
        String query = "select room_id, price_per_night from rooms order by price_per_night desc"; //View query here.
        ResultSet res = state.executeQuery(query);

        while (res.next()) {
            int room_id = res.getInt(1);
            double price_per_night = res.getDouble(2);

            System.out.println(room_id + " - " + price_per_night);//Format for output
        }
    }

    //method for searching rooms
    public void searching() throws SQLException {
        Scanner kbd = new Scanner(System.in);

        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement state = conn.createStatement();

        System.out.println("Search for room availability: ");
        String query = "select * from rooms "; //View query here.
    }
}
