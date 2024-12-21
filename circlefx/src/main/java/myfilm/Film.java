package myfilm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Film {
    String ad;
    String tur;
    String yonetmen;
    String yayinlanmatarihi;
    String usertarih;
    Long userid;

    public Film(String ad, String tur , String yonetmen, String yayinlanmatarihi) {
        this.ad = ad;
        this.tur = tur;
        this.yonetmen = yonetmen;
        this.yayinlanmatarihi = yayinlanmatarihi;
    }

    public void setUser(Long user) {
        this.userid = user;
    }
    public void setUserTarih() {
        LocalDate tarih = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formatliTarih = tarih.format(formatter);
        this.usertarih = formatliTarih;

    }
    public void deluser(){
        this.userid =null;
    }
    public void delusertarih(){
        this.usertarih=null;
    }
    public String getad(){
        return ad;
    }
    public String toString(){
        return ad+","+tur+","+yonetmen+","+yayinlanmatarihi+","+usertarih+","+userid;
    }
    public String getTur() {
        return tur;
    }
    public void setterusernumber(Long usernumber) {
        this.userid = usernumber;
    }
    public Long getusernumber() {
        return userid;
    }
    public String getUserTarih() {
        return usertarih;
    }


}
