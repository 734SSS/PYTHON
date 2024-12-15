"""from film import Film
class kiralama(Film):
    stock=[]
    
    def __init__(self, isim, tür, yönetmen, tarih, adet):
        self.isim = isim
        self.tür = tür
        self.yönetmen = yönetmen
        self.tarih = tarih
        self.adet=adet

    def stockekle(self, isim, tür, yönetmen, tarih, adet):
        
        for film in self.stock:
            if film.isim == isim:
                for i in range(adet):
                    self.stock.append(film)
                return print("filminiz stoğa eklendi")
            else:
                new_film=self.film_ekle(self, isim, tür, yönetmen, tarih)
                for i in range(adet):
                    self.stock.append(new_film)
                return print("filminiz stoğa eklendi")
            
    def stockmiktar(self, isim, tür=None, yönetmen=None, tarih=None, adet=None):
        sayi=0
        for i in self.stock:
            if i == self.isim :
                sayi+=1
            else:
                sayi+=0
        return sayi
    
    def stocksil(self, isim, adet=1):
        silinen = 0  # Silinen film sayısını takip etmek için
        yeni_stok = []  # Yeni bir liste oluşturacağız
        silinenad=None
        # Stok listesini dolaş ve silinecek olanları atla
        for film in self.stock:
            if film.isim == isim and silinen < adet:
                silinen += 1  # Belirtilen adet kadar sil
                silinenad=film
            else:
                yeni_stok.append(film)  # Diğerlerini yeni listeye ekle

        self.stock = yeni_stok  # Güncellenmiş stok listesi
        print(f"{isim} filminden {silinen} adet silindi.")
        return silinenad

    
    def stocktakiler(self):
        liste=[]
        for i in liste :
            for j in self.stock:
                if j.isim == i.isim :
                    liste.append(j)
                
        for k in liste :
            print("---------film adı : ",k.isim)
            print("yayınlanma tarihi : ",k.tarih)
            print("--------film türü : ",k.tür)
            print("---------yönetmen : ",k.yönetmen)
            print("--------------------------------------------------------------")
    
    def iade(self,isim):
        for i in Film.film_listesi :
            if i.isim == isim :
                self.stock.append(i)
            

"""
from film import Film

class kiralama(Film):
    stock = []

    def __init__(self, isim, tür, yönetmen, tarih, adet):
        super().__init__(isim, tür, yönetmen, tarih)
        self.adet = adet

    def stockekle(self, isim, tür, yönetmen, tarih, adet):
        for film in self.stock:
            if film.isim == isim:
                for _ in range(adet):
                    self.stock.append(film)
                print("Film stoğa eklendi.")
                return
        # Film stokta değilse yeni film oluştur ve ekle
        new_film = Film.film_ekle(isim, tür, yönetmen, tarih)
        for _ in range(adet):
            self.stock.append(new_film)
        print("Yeni film stoğa eklendi.")

    def stockmiktar(self, isim):
        sayi = sum(1 for film in self.stock if film.isim == isim)
        return sayi

    def stocksil(self, isim, adet=1):
        silinen = 0
        yeni_stok = []
        silinenad = None
        for film in self.stock:
            if film.isim == isim and silinen < adet:
                silinen += 1
                silinenad = film
            else:
                yeni_stok.append(film)

        self.stock = yeni_stok
        print(f"{isim} filminden {silinen} adet silindi.")
        return silinenad

    def stocktakiler(self):
        if not self.stock:
            print("Stokta film bulunmamaktadır.")
            return

        for film in self.stock:
            print("--------- Film Adı     :", film.isim)
            print("Yayınlanma Tarihi      :", film.tarih)
            print("--------- Film Türü    :", film.tür)
            print("--------- Yönetmen      :", film.yönetmen)
            print("--------------------------------------------------------------")

    def iade(self, isim):
        for film in Film.film_listesi:
            if film.isim == isim:
                self.stock.append(film)
                print(f"{isim} adlı film stoğa iade edildi.")
                return
        print(f"{isim} adlı film bulunamadı.")
