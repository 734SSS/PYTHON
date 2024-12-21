package myfilm;

import java.io.*;

public class BagliList {
    public User bas;
    public User son;

    // Kullanıcıyı bağlı listeye ekle ve dosyaya yaz
    public void ekle(User newuser) {
        if (son == null) {
            this.bas = newuser;
            this.son = newuser;
        } else {
            this.son.sonraki = newuser;
            this.son = newuser;
        }
        dosyayaKaydet(newuser);
    }

    // Kullanıcıyı dosyaya kaydet
    private void dosyayaKaydet(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(user.getUserid() + "," + user.getNumber() + "," + user.getName() + "," +
                    user.getEmail() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Dosyaya kaydetme sırasında hata: " + e.getMessage());
        }
    }

    // Kullanıcıyı bağlı listede ara
    public User ara(Long number, String password) {
        User tmp = bas;
        while (tmp != null) {
            if (tmp.getNumber().equals(number) && tmp.getPassword().equals(password)) {
                return tmp;
            }
            tmp = tmp.sonraki;
        }
        return null;
    }

    // Dosyadan kullanıcıları yükle ve bağlı listeye ekle
    public void dosyadanOku() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Long userID = Long.parseLong(parts[0]);
                Long number = Long.parseLong(parts[1]);
                String name = parts[2];
                String email = parts[3];
                String password = parts[4];

                User user = new User(number, name, email, password);
                user.setUserid(userID); // Mevcut userID'yi ayarla
                ekleDosyadan(user); // Listeye ekle (dosyaya tekrar yazma)
            }
        } catch (IOException e) {
            System.err.println("Dosyadan okuma sırasında hata: " + e.getMessage());
        }
    }

    // Dosyadan okunan kullanıcıları listeye eklerken dosyaya yazma
    private void ekleDosyadan(User newuser) {
        if (son == null) {
            this.bas = newuser;
            this.son = newuser;
        } else {
            this.son.sonraki = newuser;
            this.son = newuser;
        }
    }
}
