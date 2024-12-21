package myfilm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;


public class Yonetici {
    private List<Film> filmListesi; // İçinde sadece "Film" sınıfının sahip olduğu nesneleri barındırabilecek "filmListesi" adında bir liste oluşturuldu.

    public Yonetici() {
        filmListesi = new ArrayList<>(); // "filmListesi" değişkenine yeni bir ArrayList(dinamik olarak büyüyebilen bir liste sınıfı) nesnesi atar.
    }

    public void loadFromFile() {
        String dosyaAdi = "stock.txt"; // Dosya adı sabit olarak belirlendi

        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;   //String tipinde "satir" adlı değişken
            while ((satir = okuyucu.readLine()) != null) {  //"okuyucu.readLine", dosyayı satır-satır okumamızı sağlar.
                // Satırı ayrıştırarak Film nesnesi oluştur | Her satır bir film eder
                String[] parcalar = satir.split(","); //split metodu metni regex:"," parametresine göre ayırır. Ve anlık olarak ayrılan parçalar, "parcalar" adlı String Listesine yazılır.
                if (parcalar.length >= 4) { // Format: ad,tur,yonetmen,yayinlanmatarihi
                    String ad = parcalar[0].trim();
                    String tur = parcalar[1].trim();
                    String yonetmen = parcalar[2].trim();
                    String yayinlanmatarihi = parcalar[3].trim();
                    Film film = new Film(ad, tur, yonetmen, yayinlanmatarihi);

                    // Kullanıcı ve tarih bilgisi varsa ekle
                    if (parcalar.length > 4) {
                        String usertarih = parcalar[4].trim();
                        film.usertarih = usertarih;

                        if (parcalar.length > 5) {
                            Long userBilgisi = Long.parseLong(parcalar[5].trim());
                            film.setterusernumber(userBilgisi);
                        }
                    }

                    filmListesi.add(film);
                }
            }
            System.out.println("Dosyadan filmler başarıyla yüklendi.");
        } catch (IOException e) {
            System.err.println("Dosyadan okuma sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    public void filmEkle(Film film) {
        filmListesi.add(film);

        String dosyaAdi = "stock.txt";
        String veri = film.toString();

        try (FileWriter yazici = new FileWriter(dosyaAdi, true)) {
            yazici.write(veri + "\n");
            System.out.println("veri dosyaya yazıldı");
        } catch (IOException e) {
            System.err.println("Dosyaya yazılırken bir hata meydana geldi:" + e.getMessage());
        }
        /*
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = okuyucu.readLine()) != null) {
                System.out.println(satir); // Her satırı yazdırıyoruz
            }
        }
        catch (IOException e) {
            System.err.println("Dosyadan okuma sırasında bir hata oluştu: " + e.getMessage());
        }
        */
    }

    /// ///////////////////////////////////


    public void filmBilgisiSifirla(String hedefFilm, Long kullaniciNumarasi) {
        String dosyaAdi = "stock.txt";
        for (Film film : filmListesi) {
            if (film.getad() == hedefFilm && film.getusernumber() == kullaniciNumarasi) {
                film.setUser(null);
            }
        }
        try {
            // 1. Dosyayı oku ve satırları listeye al
            List<String> satirlar = new ArrayList<>();
            try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
                String satir;
                while ((satir = okuyucu.readLine()) != null) {
                    satirlar.add(satir);
                }
            }

            // 2. İlgili satırı bulun ve user ile tarih bilgisini null yapın
            boolean bulundu = false;
            for (int i = 0; i < satirlar.size(); i++) {
                String satir = satirlar.get(i);
                if (satir.contains(hedefFilm)) { // Film adını içeren satırı bul
                    String[] parcalar = satir.split(",");
                    if (parcalar.length >= 6 && kullaniciNumarasi.equals(parcalar[5])) { // Kullanıcı numarasını kontrol et
                        parcalar[4] = "null"; // Tarih bilgisini null yap
                        parcalar[5] = "null"; // Kullanıcı bilgisini null yap
                        satirlar.set(i, String.join(",", parcalar)); // Güncel satırı listeye yaz
                        bulundu = true;
                        break; // İşlem tamamlandı
                    }
                }
            }

            if (!bulundu) {
                System.out.println("Film bulunamadı ya da kullanıcı numarası eşleşmedi: " + hedefFilm);
                return;
            }

            // 3. Tüm listeyi dosyaya geri yaz
            try (BufferedWriter yazici = new BufferedWriter(new FileWriter(dosyaAdi))) {
                for (String satir : satirlar) {
                    yazici.write(satir);
                    yazici.newLine();
                }
            }

            System.out.println("Film bilgisi başarıyla sıfırlandı.");

        } catch (IOException e) {
            System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    private void filmBilgisiGuncelle(String hedefFilm, Long yeniKullanici, String yeniTarih) {
        String dosyaAdi = "stock.txt";
        for (Film film : filmListesi) {
            if (film.getad() == hedefFilm) {
                film.setUser(yeniKullanici);
            }
        }
        LocalDate tarih = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        String formatliTarih = tarih.format(formatter);
        String yenitarih = formatliTarih;
        try {
            List<String> satirlar = new ArrayList<>();
            try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosyaAdi))) {
                String satir;
                while ((satir = okuyucu.readLine()) != null) {
                    satirlar.add(satir);
                }
            }

            // 2. İlgili satırı bulun ve güncelleyin
            boolean bulundu = false;
            for (int i = 0; i < satirlar.size(); i++) {
                String satir = satirlar.get(i);
                if (satir.contains(hedefFilm)) { // Film adını içeren satırı bul
                    String[] parcalar = satir.split(",");
                    if (parcalar.length >= 4) {
                        parcalar[4] = yeniTarih; // usertarih bilgisini güncelle
                        parcalar[5] = String.valueOf(yeniKullanici); // user bilgisini güncelle
                        satirlar.set(i, String.join(",", parcalar)); // Güncel satırı listeye yaz
                        bulundu = true;
                        break; // İşlem tamamlandı
                    }
                }
            }

            if (!bulundu) {
                System.out.println("Film bulunamadı: " + hedefFilm);
                return;
            }

            // 3. Tüm listeyi dosyaya geri yaz
            try (BufferedWriter yazici = new BufferedWriter(new FileWriter(dosyaAdi))) {
                for (String satir : satirlar) {
                    yazici.write(satir);
                    yazici.newLine();
                }
            }

            System.out.println("Film başarıyla güncellendi.");

        } catch (IOException e) {
            System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
        }

    }

    /// /////////////////////////////////////////////////////////////////////////////

    public void kiralama(String filmAdi, Long kullaniciNumarasi, String tarih) {
        filmBilgisiGuncelle(filmAdi, kullaniciNumarasi, tarih);
    }

    public void iade(String filmAdi, Long kullaniciNumarasi, String tarih) {
        filmBilgisiGuncelle(filmAdi, kullaniciNumarasi, tarih);
    }

    public List<Film> adminlistele() {
        return filmListesi;
    }

    public List<Film> userlistele(Long kullaniciNumarasi) {
        for (Film film : filmListesi) {
            if (film.getusernumber() == kullaniciNumarasi) {
                List<Film> userfilmler = new ArrayList<>();
                userfilmler.add(film);
            }

        }
        return filmListesi;
    }

    public List<Film> kiralanabilirler() {
        List<Film> kiralanabilir = new ArrayList<>();                                    // "kiralanabilir": ArrayList tipinde bir liste.
        for (Film film : filmListesi) {                                                  // "filmListesi" elemanlarını dolaşıyor.
            if (film.getusernumber() == null) {                                          // her bir elemanın, yani "film" nesnesinin "usernumber" değerini kontrol eder.
                kiralanabilir.add(film);                                                 // değer null ise film kimseye ait değildir. Film, "kiralanabilir" listesine eklenir.
            }
        }
        return kiralanabilir;                                                            // "kiralanabilir" return et.
    }


    public List<Film> turlisteleadmin(String tur) {                                      // Tür'e göre filtreleme amaçlı, manuel girilecek olan "tur" parametresine sahip sıralama fonksiyonu
        List<Film> turfilm = new ArrayList<>();                                          // "turfilm" : sadece Film sınıfı niteliklerine sahip olabilecek, ArrayList tipinde bir liste.
        for (Film film : filmListesi) {                                                  // "filmListesi" elemanlarını dolaşıyor.
            if ((film.tur) == tur) {                                                     // Mevcut index'deki film 'in "tur" niteliği, parametre olarak girilen "tur" niteliğine denk mi?
                turfilm.add(film);                                                       // Şart sağlanıyorsa film, "turfilm" listesine eklenir.
            }
        }
        return turfilm;                                                                  // "turfilm" return et.
    }
    public List<Film> turlisteleuser(String tur) {                                       // Bu fonksiyonun üstteki fonksiyondan farkı; Kiralanan bir filmin döndürülmeyeceğidir.
        List<Film> turfilm = new ArrayList<>();                                          // Tür filmlerini saklayacak liste

        for (Film film : filmListesi) {                                                  // "filmListesi" elemanlarını dolaşıyor.
            if (film.getTur().equals(tur) && film.getusernumber() == null) {             // Mevcut index'deki film 'in "tur" niteliği, parametre olarak girilen "tur" niteliğine denk mi? VE yine Mevcut index'deki film 'in usernumber niteliği null 'mu? Yani birisi tarafından alınmış mı?
                turfilm.add(film);                                                       // Şart sağlanıyorsa film, "turfilm" listesine eklenir
            }
        }

        return turfilm;                                                                  // "turfilm" return et.
    }

    public List<Film> tarihlistele(){                                                    // Bir film 'in ne zaman kiralandığını öğrenmek için yazılmış bir fonksiyon
        List<Film> tarihfilm = new ArrayList<>(); // Tür filmlerini saklayacak liste
        for (Film film : filmListesi) {
            if (film.getusernumber() != null) {
                // Tür eşit ve kullanıcı atanmadıysa
                tarihfilm.add(film); // Filmi listeye eklex
            }
        }
        return tarihlerineGoreSirala(tarihfilm);// Filtrelenmiş listeyi döndür

    }

    private List<Film> tarihlerineGoreSirala(List<Film> tarihFilmListesi) {
        // Tarih formatını belirtiriz.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        // Listede sıralama işlemi için Comparator kullanıyoruz.
        Collections.sort(tarihFilmListesi, (film1, film2) -> {
            // String tarihleri LocalDate'e dönüştürüp karşılaştırıyoruz.
            LocalDate tarih1 = LocalDate.parse(film1.getUserTarih(), formatter);
            LocalDate tarih2 = LocalDate.parse(film2.getUserTarih(), formatter);
            return tarih1.compareTo(tarih2); // Tarihleri karşılaştırıp sıralama yapıyoruz.
        });

        return tarihFilmListesi; // Sıralanmış listeyi geri döndürüyoruz.
    }
}


