
/**
 *
 * @author Acosta
 */
public class Accounts {
private String clientId;
private String fname;
private String lname;
private String email;
private String phoneno;
    public Accounts(){

    }

    public Accounts(String clientId,String fname,String lname, String email, String phoneno) {
        this.clientId = clientId;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phoneno = phoneno;
    }     
    public String getClientId() {
        return clientId;
    }
    
    public String getName() {
        return fname + " " + lname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhoneNo() {
        return phoneno;
    }
}
