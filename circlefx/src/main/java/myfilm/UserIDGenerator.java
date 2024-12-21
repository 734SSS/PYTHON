package myfilm;

public class UserIDGenerator {
    private static long currentID = 0; // Başlangıç ID değeri

    //generateID : yeni bir kullanıcı yaratırken atanacak ID 'nin oluşturulması
    public static long generateID() {
        return ++currentID;
    }
}