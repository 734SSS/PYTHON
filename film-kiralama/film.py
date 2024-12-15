"""class Film:
    film_listesi = []  

    def __init__(self, isim, tür, yönetmen, tarih):
        self.isim = isim
        self.tür = tür
        self.yönetmen = yönetmen
        self.tarih = tarih

    
    def film_ekle(cls, isim, tarih, tür, yönetmen):
        for film in cls.film_listesi:
            if film.isim == isim:
                print(f"Film '{isim}' zaten listede.")
                return film
        
        yeni_film = cls(isim, tür, yönetmen, tarih)
        cls.film_listesi.append(yeni_film)
        print(f"Yeni film '{isim}' eklendi.")
        return yeni_film
    
    def filmsil(self,isim):
        i=-1
        for film in self.film_listesi:
            i+=1
            if film.isim == isim:
                self.film_listesi.pop(i)
                return film
        
        return print("silmek istediğiniz film kayıt edilmemiş")

    def filmlistele(self):
        for film in self.film_listesi:
            print("---------film adı : ",film.isim)
            print("yayınlanma tarihi : ",film.tarih)
            print("--------film türü : ",film.tür)
            print("---------yönetmen : ",film.yönetmen)
            print("--------------------------------------------------------------")

    """
class Film:
    film_listesi = []  # Film listesini sınıf düzeyinde tutuyoruz

    def __init__(self, isim, tür, yönetmen, tarih):
        self.isim = isim
        self.tür = tür
        self.yönetmen = yönetmen
        self.tarih = tarih

    @classmethod
    def film_ekle(cls, isim, tür, yönetmen, tarih):
        for film in cls.film_listesi:
            if film.isim == isim:
                print(f"Film '{isim}' zaten listede.")
                return film

        yeni_film = cls(isim, tür, yönetmen, tarih)
        cls.film_listesi.append(yeni_film)
        print(f"Yeni film '{isim}' eklendi.")
        return yeni_film

    @classmethod
    def filmsil(cls, isim):
        for i, film in enumerate(cls.film_listesi):
            if film.isim == isim:
                cls.film_listesi.pop(i)
                print(f"Film '{isim}' silindi.")
                return film
        
        print("Silmek istediğiniz film kayıtlı değil.")
        return None

    @classmethod
    def filmlistele(cls):
        if not cls.film_listesi:
            print("Film listesi boş.")
            return

        for film in cls.film_listesi:
            print("--------- Film Adı     :", film.isim)
            print("Yayınlanma Tarihi      :", film.tarih)
            print("--------- Film Türü    :", film.tür)
            print("--------- Yönetmen      :", film.yönetmen)
            print("--------------------------------------------------------------")
