package myfilm;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Yonetici manager = new Yonetici();

        // Birkaç film oluştur ve ekle
        Film film1 = new Film("Inception", "Bilim Kurgu", "Christopher Nolan", "2010");
        Film film2 = new Film("Interstellar", "Bilim Kurgu", "Christopher Nolan", "2014");

        // Filmleri ekle
        manager.filmEkle(film1);
        manager.filmEkle(film2);
        //manager.kiralama("Interstellar", 001L, "17.12.2024");
        manager.filmBilgisiSifirla("Interstellar", 001L);


        // Yerel tarihi al
        LocalDate tarih = LocalDate.now();
        // Formatlı bir şekilde döndür
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formatliTarih = tarih.format(formatter);

        System.out.println("Bugünün tarihi: " + formatliTarih);
    }
}
