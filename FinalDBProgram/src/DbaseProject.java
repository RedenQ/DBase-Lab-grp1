
import java.sql.*;
import java.time.Year;
import java.util.*;

/**
 *
 * @author Ungson
 */
public class DbaseProject {

    //global variables and objects 
    public static Scanner kbd = new Scanner(System.in);
    public static String url = "jdbc:mysql://localhost/hotel";
    public static String user = "root";
    public static String pass = "";
    public static int choice = 0;
    public static DbaseProject db = new DbaseProject();
    public static Accounts clientAcc = null;

    //main method
    public static void main(String[] args) {

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            while (choice != 1 || choice != 2) {
                System.out.println("           WELCOME TO ACCOMODATION RESERVATION");
                System.out.println("------------------------------------------------------------");
                System.out.print(" 1.) Register \n 2.) View available accomodations \n");
                System.out.println(" ");
                System.out.print("Enter the number of your choice: ");
                choice = kbd.nextInt();
                switch (choice) {
                    case 1:
                        db.register(conn);
                        break;
                    case 2:
                        db.viewHotel(conn);
                        break;
                }
                if (choice != 1 || choice != 2) {
                    System.out.println("Please enter a valid input!");
                    System.out.println(" ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method for viewing available rooms
    public void viewHotel(Connection conn) throws SQLException {

        while (choice != 1 || choice != 2 || choice != 3) {
            Statement myStatement = conn.createStatement();
            System.out.println(" ");
            String query = "select * from hotel";//View query here.
            ResultSet res = myStatement.executeQuery(query);
            System.out.println(" ");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.println("HOTEL_ID    HOTEL_NAME    HOTEL_ADDRESS");
            System.out.println(" ");
            while (res.next()) {
                int hotel_id = res.getInt(1);
                String hotel_name = res.getString(2);
                String hotel_address = res.getString(3);
                System.out.println(hotel_id + "    -    " + hotel_name + "    -    " + hotel_address);//Format for output
                System.out.println(" ");//Format for output
            }
            System.out.println(" ");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.println(" ");

            System.out.println("What do you want to do next?  ");
            System.out.println(" ");
            System.out.println(" (1) Book now \n (2) Search by hotel name \n (3) Exit");
            System.out.println(" ");
            System.out.print("Enter the number of your choice: ");
            choice = kbd.nextInt();
            switch (choice) {
                case 1:
                    db.login(conn);
                    break;
                case 2:
                    db.searching(conn);
                    break;
                case 3:
                    System.out.println("BYE");
                    break;

            }
            if (choice != 1 || choice != 2 || choice != 3) {
                System.out.println("Please enter a valid input!");
                System.out.println(" ");
            }
        }
    }

    //method for logging in
    public void login(Connection conn) throws SQLException {
        Statement state = conn.createStatement();
        try {
            while (choice != 1 || choice != 2) {
                System.out.println(" ");
                System.out.println("   LOGIN FIRST BEFORE BOOKING");
                System.out.print("Do you have an existing account? Enter (1) if yes, otherwise (2): ");
                choice = kbd.nextInt();
                if (choice == 1) {
                    System.out.println("Enter a valid account...");
                    System.out.print("email: ");
                    String email = kbd.next();
                    System.out.print("password: ");
                    String password = kbd.next();
                    ResultSet emailSet = state.executeQuery("SELECT * FROM client WHERE email = '" + email + "' AND AES_DECRYPT(password, 'key') = '" + password + "'");
                    if (emailSet.next()) {
                        clientAcc = new Accounts(emailSet.getString("client_id"),emailSet.getString("fname"),emailSet.getString("lname"),emailSet.getString("email"),emailSet.getString("phoneno"));
                        db.promptClient(conn);
                    } else if (!emailSet.next()) {
                        int regChoice;
                        System.out.println(" ");
                        System.out.print(" User not register, do you want to register(yes/no): \n Press (1) if yes, (2) if no: ");
                        regChoice = kbd.nextInt();
                        switch (regChoice) {
                            case 1:
                                db.register(conn);
                                break;
                            case 2:
                                System.out.println("BYE");
                                break;
                        }
                    }
                } else {
                    while (choice != 1 || choice != 2) {
                        System.out.println(" Do you want to signup? \n (1) Yes \n (2) No");
                        System.out.print("Enter the number of your choice: ");
                        choice = kbd.nextInt();
                        switch (choice) {
                            case 1:
                                db.register(conn);
                                break;
                            case 2:
                                System.out.println("BYE");
                                break;
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method for registering account
    public void register(Connection conn) throws SQLException {
        Scanner kbd = new Scanner(System.in);
        try {
            String fname, lname, phoneno, email, password,password2;
            Statement state = conn.createStatement();
            do {
                
                
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
                System.out.print("Re-enter password: ");
                password2 = kbd.nextLine();
                if(password.equals(password2)) {
                    break;
                }
                System.out.print("Passwords must be matching");
            } while(true);
            
            String check = "select email from client where email = '" + email + "'";
            ResultSet rs = state.executeQuery(check);

            if (rs.next()) {
                System.out.println("\nEmail has already been used! \n Press (1) Login (2) Register");
                choice = kbd.nextInt();
                switch (choice) {
                    case 1:
                        login(conn);
                        break;
                    case 2:
                        register(conn);
                        break;
                }
            } else {
                String query = "insert into client(fname, lname, phoneno, email, password) values('" + fname + "','" + lname + "' ,'" + phoneno + "' ,'" + email + "' , AES_ENCRYPT('" + password + "', 'key'))"; //View query here.
                state.executeUpdate(query);
                System.out.println("Your account has successfully registered!!! ");
                System.out.print(" ");
                System.out.print("Do you want to view hotel? Press (1) if yes, (2) otherwise: ");
                choice = kbd.nextInt();

                switch (choice) {
                    case 1:
                        db.viewHotel(conn);
                        break;
                    case 2:
                        System.out.println("BYE");
                        break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method for searching by hotel
    public void searching(Connection conn) throws SQLException {
        Scanner kbd = new Scanner(System.in);
        Statement state = conn.createStatement();

        System.out.print("Search by hotel name: ");
        String name = kbd.nextLine();

        String query = "select * from hotel where hotel_name = '" + name + "'"; //View query here.
        ResultSet rs = state.executeQuery(query);
        if (!rs.next()) {
            System.out.println(" There is no corresponding result on your input... \n Please try again...");
            db.searching(conn);
        } else {
            System.out.println(" ");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.println("Available hotel/s that corresponds to your search: ");
            System.out.println(" ");
            System.out.println("HOTEL_ID    HOTEL_NAME    HOTEL_ADDRESS");
            System.out.println(" ");
            int hotel_id = rs.getInt(1);
            String hotel_name = rs.getString(2);
            String hotel_address = rs.getString(3);
            System.out.println(hotel_id + "    -    " + hotel_name + "    -    " + hotel_address);//Format for output
            System.out.println(" ");//Format for output
            System.out.println(" ");
            System.out.println("--------------------------------------------------------------------------------------------------------------");
            System.out.println(" ");
            System.out.print("Do you want to go back to view hotel? Press (1) if yes, (2) otherwise: ");
            choice = kbd.nextInt();

            switch (choice) {
                case 1:
                    db.viewHotel(conn);
                    break;
                case 2:
                    System.out.println("BYE");
                    break;
            }
        }

    }

    // This will be the method to prompt after successfully logged in
    public void promptClient(Connection conn) throws SQLException {
        System.out.println("      ");
        System.out.println("      BOOK YOUR RESERVATION NOW");
        System.out.println("---------------------------------------");
        System.out.print(" (1) BOOK NOW \n (2) EXIT \n Enter the number of your choice: ");
        choice = kbd.nextInt();

        switch (choice) {
            case 1:
                bookNow(conn);
                break;
            case 2:
                System.out.println("BYE");
                break;
        }
    }

    //Method bookNow()
    public void bookNow(Connection conn) throws SQLException {
        Statement state = conn.createStatement();
        String query = "Select * from hotel";
        ResultSet res = state.executeQuery(query);

        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.println(" ");
        System.out.println("HOTEL_ID    HOTEL_NAME    HOTEL_ADDRESS");
        System.out.println(" ");
        while (res.next()) {
            int hotel_id = res.getInt(1);
            String hotel_name = res.getString(2);
            String hotel_address = res.getString(3);
            System.out.println(hotel_id + "    -    " + hotel_name + "    -    " + hotel_address);//Format for output
            System.out.println(" ");//Format for output
        }
        System.out.println(" ");
        System.out.println("--------------------------------------------------------------------------------------------------------------");
        System.out.print("Enter the corresponding hotel_id of the hotel name to view the available rooms: ");
        int hotelid = kbd.nextInt();
        ResultSet hname = state.executeQuery("select hotel_name from hotel where hotel_id = '" + hotelid + "'");
        while (hname.next()) {
            String hotel_name = hname.getString(1);
            System.out.println(" ");
            System.out.println("Here are the available rooms in " + hotel_name + ": ");
        }
        ResultSet rs = state.executeQuery("SELECT * from rooms where hotel_id = '" + hotelid + "'");
        System.out.println("----------------------------------------------------------");
        System.out.println("room_id   accomodation   availability   price_per_night");
        
        while (rs.next()) {
            int room_id = rs.getInt(1);
            String Accomodation = rs.getString(2);
            String Availability = rs.getString(3);
            double price_per_night = rs.getDouble(4);
            System.out.println("  " + room_id + "    -    " + Accomodation + "    -    " + Availability + "    -    " + price_per_night);//Format for output

        }
        System.out.println("----------------------------------------------------------");
        System.out.print("What do you want to do now? \n (1) Display rooms from cheapest to most expensive"
                + "\n (2) Display rooms from most expensive to cheapest \n (3) Back to view hotel \n (4) Select room"
                + "\n (5) exit \n Enter the number of your choice: ");
        choice = kbd.nextInt();
        switch (choice) {
            case 1:
                ResultSet asc = state.executeQuery("select * from rooms where hotel_id = '" + hotelid + "' order by price_per_night asc ");
                System.out.println(" ");
                System.out.println("This is listed from cheapest to most expensive rooms...");//Format for output
                System.out.println("----------------------------------------------------------");
                System.out.println("room_id   accomodation   availability   price_per_night");
                while (asc.next()) {
                    int room_id = asc.getInt(1);
                    String Accomodation = asc.getString(2);
                    String Availability = asc.getString(3);
                    double price_per_night = asc.getDouble(4);
                    System.out.println("  " + room_id + "    -    " + Accomodation + "    -    " + Availability + "    -    " + price_per_night);//Format for output
                }
                System.out.println(" ");
                System.out.print("Do you want to book now? Press (1) if yes, (2) otherwise: ");
                choice = kbd.nextInt();

                switch (choice) {
                    case 1:
                        db.bookNow(conn);
                        break;
                    case 2:
                        System.out.println("BYE");
                        break;
                }
                break;
            case 2:
                ResultSet desc = state.executeQuery("select * from rooms where hotel_id = '" + hotelid + "' order by price_per_night desc ");
                System.out.println(" ");
                System.out.println("This is listed from most expensive to cheapest rooms...");//Format for output
                System.out.println("----------------------------------------------------------");
                System.out.println("room_id   accomodation   availability   price_per_night");
                while (desc.next()) {
                    int room_id = desc.getInt(1);
                    String Accomodation = desc.getString(2);
                    String Availability = desc.getString(3);
                    double price_per_night = desc.getDouble(4);
                    System.out.println("  " + room_id + "    -    " + Accomodation + "    -    " + Availability + "    -    " + price_per_night);//Format for output
                }
                System.out.println(" ");
                System.out.print("Do you want to book now? Press (1) if yes, (2) otherwise: ");
                choice = kbd.nextInt();

                switch (choice) {
                    case 1:
                        db.bookNow(conn);
                        break;
                    case 2:
                        System.out.println("BYE");
                        break;
                }
                break;
            case 3:
                db.bookNow(conn);
                break;
            case 4:
                Scanner kbd = new Scanner(System.in);
                System.out.println(" ");
                int roomchoice;
                ResultSet rt;
                String roomtype = "";
                do {
                    System.out.println("Here are the list of room types: ");
                    System.out.println(" (1) single - This room is for 1 person \n"
                            + " (2) double - This room is for 2 persons \n"
                            + " (3) triple - This room is for 3 persons \n"
                            + " (4) quad - This room is for 4 persons \n"
                            + " (5) family - This room is for 4 - 6 persons \n"
                            + " (6) suite - This room is for 6 - 10 persons \n"
                            + " (7) barkada - This room is for 10 - 15 persons");
                    System.out.println(" ");

                    System.out.print("Enter the number of the roomtype you want to avail: ");
                    roomchoice = kbd.nextInt();
                    switch (roomchoice) {
                        case 1 :  
                            roomtype = "single";
                            break;
                        case 2 :
                             roomtype = "double";
                             break;
                        case 3 :
                             roomtype = "triple";
                             break;
                        case 4 :
                             roomtype = "quad";
                             break;
                        case 5 :
                             roomtype = "family";
                             break;
                        case 6 :
                             roomtype = "suite";
                             break;
                        case 7 :
                             roomtype = "barkada";
                             break;
                        default :
                            System.out.print("Invalid choice");
                    }
                }while (roomchoice <= 0 || roomchoice >=8);
                System.out.println("");
                System.out.println("room_id  type  availableRooms  pricePerNight"); 
                rt = state.executeQuery("select * from rooms where type = '" + roomtype + "' and hotel_id = '" + hotelid + "'");

                while (rt.next()) {
                    int room_id = rt.getInt(1);
                    String type = rt.getString(2);
                    int Availability = rt.getInt(3);
                    double price_per_night = rt.getDouble(4);
                    System.out.println(room_id + "  -  " + type + "  -  " + Availability + "  -  " + price_per_night);

                    if (Availability == 0) {
                        System.out.println("The room you've selected is not available... \n select "
                                + "other room instead... ");
                        System.out.print("Do you want to go back to booking? \n Press (1) Yes, (2) otherwise");
                        choice = kbd.nextInt();

                        switch (choice) {
                            case 1:
                                db.bookNow(conn);
                                break;
                            case 2:
                                System.out.println("BYE");
                                break;
                        }
                    } else {
                        System.out.println(" ");
                        System.out.print("Enter the room_id to confirm your booking: ");
                        choice = kbd.nextInt();
                        System.out.print("When do you plan on checking in: \nInput date MM-DD");
                        String checkindate = kbd.nextLine();
                        String reformatdate = Year.now().getValue()+"-"+checkindate;
                        
                        int checkout;
                        do {
                            System.out.print("How many nights do you plan on staying?");
                            checkout = kbd.nextInt();
                        } while (checkout <= 0 || checkout >= 31);
                        String insReservation = "INSERT INTO reservation(check_in_date,check_in_time,check_out_date,check_out_time,client_id,room_id) values ("+reformatdate+",CURTIME(),DATE_ADD("+reformatdate+",INTERVAL "+checkout+" DAYS),CURTIME(),"+clientAcc.getClientId()+","+room_id+")";
                        state.executeUpdate(insReservation);
                        //insert to reservation
                        String update = "update rooms set Availability = (Availability - 1) where room_id = '" + choice + "'";
                        state.executeUpdate(update);
                        System.out.println("------------------------------- ");
                        System.out.println("YOU HAVE SUCCESSFULLY BOOKED!!!");
                        System.out.println("------------------------------- ");
                        System.out.println(" ");
                        try {
                            System.out.print("Do you want to cancel booking? (1) YES, (2) NO \n Enter the number"
                                    + " of your choice: ");
                            choice = kbd.nextInt();
                            switch (choice) {
                                case 1:
                                    String cancelBook = "update rooms set Availability = (Availability + 1) where room_id = '" + choice + "'";
                                    state.executeUpdate(cancelBook);
                                    System.out.println("------------------------------------------------");
                                    System.out.println("YOU HAVE SUCCESSFULLY CANCELLED YOUR BOOKING!!!");
                                    System.out.println("------------------------------------------------");
                                    break;
                                case 2:
                                    System.out.println("Bye");
                                    break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
                break;
            case 5:
                System.out.println("BYE");
                break;
        }
    }

    public void bookRecord(Connection conn) throws SQLException {

    }

    public void cancelBooking(Connection conn) throws SQLException {

    }
}
