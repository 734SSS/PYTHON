package myfilm;

import java.util.ArrayList;
import java.util.List;

//özniteliklerin yaratılması
public class User {
    public User sonraki;
    private Long userid;
    private Long number;
    private String name;
    private String email;
    private String password;


    // Yapıcılandırıcı
    public User(Long number, String name, String email, String password) {
        this.number = number;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userid= UserIDGenerator.generateID();
    }
    //number alıcı
    public Long getNumber() {
        return number;
    }
    //number değiştirici
    public void setNumber(Long number) {
        this.number = number;
    }
    //name alıcı
    public String getName() {
        return name;
    }
    //name değiştirici
    public void setName(String name) {
        this.name = name;
    }
    //email alıcı
    public String getEmail() {
        return email;
    }
    //email değiştirici
    public void setEmail(String email) {
        this.email = email;
    }
    //password alıcı
    public String getPassword() {
        return password;
    }
    //password değiştirici
    public void setPassword(String password) {
        this.password = password;
    }
    //Userid alıcı
    public Long getUserid() {
        return userid;
    }
    //Userid değiştirici
    public void setUserid(Long userID)  {
        this.userid=userID;
    }

}




