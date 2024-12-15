"""class User:
    users = []
    
    def __init__(self, isim, soyisim, mail, telefon, sifre, filmler=None):
        self.isim = isim
        self.soyisim = soyisim
        self.mail = mail 
        self.telefon = telefon
        self.sifre = sifre
        self.filmler = filmler if filmler else []

    @classmethod
    def kayit(cls, isim, soyisim, mail, telefon, sifre):
        for user in cls.users:
            if user.mail == mail:
                print("E-posta zaten kayıtlı.")
                return None
        new_user = cls(isim, soyisim, mail, telefon, sifre)
        cls.users.append(new_user)
        print("Kayıt başarılı.")
        return new_user

    @classmethod
    def giris(cls, mail, sifre):
        for user in cls.users:
            if user.mail == mail and user.sifre == sifre:
                print("Hoş geldiniz!")
                return user
        print("E-posta veya şifre hatalı.")
        return None

    def film_kirala(self, film):
        self.filmler.append(film)  # Film nesnesini kullanıcının listesine ekle
        print(f"{film.isim} filmi kiralandı.")

    def kiralanan_filmleri_goster(self):
        if not self.filmler:
            print("Henüz bir film kiralamadınız.")
        else:
            print(f"{self.isim} {self.soyisim} kiraladığı filmler:")
            for film in self.filmler:
                print(film)
    
    def iade (self,isim):
        index=-1
        for film in self.filmler :
            index+=1
            if film.isim == isim:
                self.filmler.pop(index)
                


"""
class User:
    users = []
    
    def __init__(self, isim, soyisim, mail, telefon, sifre, filmler=None):
        self.isim = isim
        self.soyisim = soyisim
        self.mail = mail 
        self.telefon = telefon
        self.sifre = sifre
        self.filmler = filmler if filmler else []

    @classmethod
    def kayit(cls, isim, soyisim, mail, telefon, sifre):
        for user in cls.users:
            if user.mail == mail:
                print("E-posta zaten kayıtlı.")
                return None
        new_user = cls(isim, soyisim, mail, telefon, sifre)
        cls.users.append(new_user)
        print("Kayıt başarılı.")
        return new_user

    @classmethod
    def giris(cls, mail, sifre):
        for user in cls.users:
            if user.mail == mail and user.sifre == sifre:
                print("Hoş geldiniz!")
                return user
        print("E-posta veya şifre hatalı.")
        return None

    def film_kirala(self, film):
        self.filmler.append(film)  # Film nesnesini kullanıcının listesine ekle
        print(f"{film.isim} filmi kiralandı.")

    def kiralanan_filmleri_goster(self):
        if not self.filmler:
            print("Henüz bir film kiralamadınız.")
        else:
            print(f"{self.isim} {self.soyisim} kiraladığı filmler:")
            for film in self.filmler:
                print(f"Film adı: {film.isim}")

    def iade(self, isim):
        for index, film in enumerate(self.filmler):
            if film.isim == isim:
                self.filmler.pop(index)
                print(f"{isim} filmi iade edildi.")
                return film
        print(f"{isim} adlı film kiralanmamış.")
        return None
