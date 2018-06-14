package eva.example.com.chanjo;

/**
 * Created by eva on 10/4/16.
 */
public class Contact {
    String user, phone, pass;
    public void setUser(String user){
        this.user = user;
    }
    public String getUser(){
        return this.user;
    }
    public void setPhone (String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPass(String pass){
        this.pass = pass;
    }
    public String getPass(){
        return this.pass;
    }
public Contact(String user,String phone,String pass){
    this.user=user;
    this.phone=phone;
    this.pass=pass;
}
}
