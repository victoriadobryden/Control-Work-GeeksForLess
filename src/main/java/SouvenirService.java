import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class SouvenirService {
     public static void addSouvenir(HashMap<Integer, Manufacturer> mf, HashMap<Integer, Souvenir> sv, Scanner sc,
                                    int svId) {
          ManufacturerService.viewManufacturers(mf);
          System.out.println("Enter ID of manufacturer, what you want to add souvenir to");
          int ID = sc.nextInt();
          System.out.println("Enter name, release date, and price of souvenir");
          String s = sc.nextLine();
          sv.put(svId++, new Souvenir(s.split(" ")[0], ID, LocalDate.parse(s.split(" ")[1]),
                  Double.parseDouble(s.split(" ")[2])));
     }

     public static void viewManufacturers() {

     }

     public static void editManufacturers() {

     }


     //public void editSouvenir(int key, )
}
