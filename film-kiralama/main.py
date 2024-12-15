from user import User
from film import Film
from kiralama import kiralama

adminsifre = "tiltunç"
print("-------------------------")
print("Film Kiralama Uygulaması")  # Bu main dosyası
print("Kayıt: 0")
print("Giriş: 1")
print("Çıkış: 2")
print("Admin panel: 3")

giriswhile = 1
kullanici = None
kiralama_nesnesi = kiralama("Film Adı", "Tür", "Yönetmen", "Tarih", 0)  # Kiralama işlemleri için bir nesne

while giriswhile > 0:
    try:
        giris = int(input(">>> "))
    except ValueError:
        print("Lütfen bir sayı girin.")
        continue

    if giris == 0:  # Kayıt işlemi
        isim = input("İsim girin: ").strip()
        soyisim = input("Soyisim girin: ").strip()
        telefon = input("Telefon girin: ").strip()
        mail = input("Mail girin: ").strip()
        sifre = input("Şifre girin: ").strip()
        kullanici = User.kayit(isim, soyisim, mail, telefon, sifre)

    elif giris == 1:  # Giriş işlemi
        mail = input("Mail girin: ").strip()
        sifre = input("Şifre girin: ").strip()
        kullanici = User.giris(mail, sifre)

    elif giris == 2:  # Çıkış işlemi
        print("Çıkış yapılıyor...")
        giriswhile = 0

    elif giris == 3:  # Admin panel
        sifregir = input("Şifreyi girin: ")
        if sifregir == adminsifre:
            print("Admin paneline hoş geldiniz")
            print("Film eklemek için 1")
            print("Stok eklemek için 2")
            print("Film silmek için 3")
            print("Stok silmek için 4")
            istek = int(input("Seçiminizi yapın: "))

            if istek == 1:
                film_adı = input("Film adı: ")
                film_tür = input("Filmin türünü girin: ")
                film_yönetmen = input("Filmin yönetmenini girin: ")
                film_tarihi = input("Filmin yayınlanma tarihini girin: ")
                Film.film_ekle(film_adı, film_tür, film_yönetmen, film_tarihi)

            elif istek == 2:
                film_adı = input("Film adı: ")
                film_tür = input("Filmin türünü girin: ")
                film_yönetmen = input("Filmin yönetmenini girin: ")
                film_tarihi = input("Filmin yayınlanma tarihini girin: ")
                film_adet = int(input("Film adedini girin: "))
                kiralama_nesnesi.stockekle(film_adı, film_tür, film_yönetmen, film_tarihi, film_adet)

            elif istek == 3:
                film_adı = input("Film adı: ")
                Film.filmsil(film_adı)

            elif istek == 4:
                film_adı = input("Film adı: ")
                film_miktar = kiralama_nesnesi.stockmiktar(film_adı)
                print(f"{film_miktar} adet film var. Kaç tane silmek istersiniz?")
                sil_miktar = int(input("Kaç tane silmek istersiniz: "))
                if sil_miktar <= film_miktar:
                    kiralama_nesnesi.stocksil(film_adı, sil_miktar)
                else:
                    print("O kadar mevcut değil, tekrar girin.")
        else:
            print("Yanlış şifre.")

    # Kiralama işlemleri
    if kullanici is not None:
        print("Yapmak istediğiniz işlemi seçin")
        print("Stoktaki filmleri listelemek için 1")
        print("Kiraladığınız filmleri görmek için 2")
        print("Film kiralamak için 3")
        print("Film iade etmek için 4")
        işlem = int(input("İşlemi seçiniz: "))

        if işlem == 1:
            kiralama_nesnesi.stocktakiler()

        elif işlem == 2:
            kullanici.kiralanan_filmleri_goster()

        elif işlem == 3:
            kiralama_nesnesi.stocktakiler()
            istenen = input("İstediğiniz filmin adını girin: ")
            kiralanan = kiralama_nesnesi.stocksil(istenen)
            if kiralanan:
                kullanici.film_kirala(kiralanan)

        elif işlem == 4:
            kullanici.kiralanan_filmleri_goster()
            iade_isim = input("İade etmek istediğiniz filmin adını girin: ")
            kullanici.iade(iade_isim)
            kiralama_nesnesi.iade(iade_isim)
