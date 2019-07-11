
import java.sql.*;
import java.util.*;

/**
 *
 * @author
 */
public class DbaseProject {

    //global variables and objects 
    public static Scanner kbd = new Scanner(System.in);
    public static String url = "jdbc:mysql://localhost/hotel";
    public static String user = "root";
    public static String pass = "";
    public static int choice = 0;
    public static DbaseProject db = new DbaseProject();

    //main method
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            System.out.print(" 1.) Register \n 2.) View available accomodations \n");
            System.out.println(" ");
            System.out.print("Enter the number of your choice: ");
            choice = kbd.nextInt();
            switch (choice) {
                case 1:
                    db.register();
                    break;
                case 2:
                    db.viewAcc(conn);
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
            System.out.println(" ");//Format for output
        }
        System.out.println("What do you want to do next?  ");
        System.out.println(" (1) Display from cheapest to most expensive \n (2) Display from most expensive to cheapest"
                + " \n (3) Book now \n (4) Nevermind");
        System.out.print("Enter the number of your choice: ");
        choice = kbd.nextInt();
        switch (choice) {
            case 1:
                db.sortAccAsc();
                break;
            case 2:
                db.sortAccDes();
                break;
            case 3:
                db.login(conn);
                break;
            case 4:
                System.out.println("BYE");
                break;
        }
    }

    //method for logging in
    public void login(Connection conn) throws SQLException {
        Statement state = conn.createStatement();
        Scanner kbd = new Scanner(System.in);
        try {
            System.out.println(" ");
            System.out.println("   LOGIN FIRST BEFORE BOOKING");
            System.out.print("Do you have an existing account? Enter (1) if yes, otherwise (2): ");
            choice = kbd.nextInt();
            if (choice == 1) {
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
                            System.out.println("BYE!!!!");
                            break;
                    }
                }
            } else {
                db.register();
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
            System.out.println("  ");
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

            String check = "select email from client where email = '" + email + "'";
            ResultSet rs = state.executeQuery(check);

            if (rs.next()) {
                System.out.println("\nEmail has already been used! \n Press (1) Login (2) Register");
                choice = kbd.nextInt();
                switch (choice) {
                    case 1:
                        login(conn);
                    case 2:
                        register();
                }
            } else {
                String query = "insert into client(fname, lname, phoneno, email, password) values('" + fname + "','" + lname + "' ,'" + phoneno + "' ,'" + email + "' ,'" + password + "' )"; //View query here.
                state.executeUpdate(query);
                System.out.print("Your account has successfully registered!!! ");
                System.out.print("Do you want to login? \nPress(1) if yes (2) if no, to go back to view accomodations press (3): ");
                choice = kbd.nextInt();
                switch (choice) {
                    case 1:
                        db.login(conn);
                        break;
                    case 2:
                        System.out.println("BYE");
                        break;
                    case 3:
                        db.viewAcc(conn);
                        break;
                }
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

    //method for searching by hotel
    public void searching() throws SQLException {
        Scanner kbd = new Scanner(System.in);

        Connection conn = DriverManager.getConnection(url, user, pass);
        Statement state = conn.createStatement();
        String name;

        System.out.println("Search by hotel name: ");
        name = kbd.nextLine();
        
        String query = "select name from rooms where name = '" + name + "'"; //View query here.
        ResultSet rs = state.executeQuery(query);
        
        System.out.println(rs);
    }
}
