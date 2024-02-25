import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public HashMap<Integer, Manufacturer> mf = new HashMap<>();
    public HashMap<Integer, Souvenir> sv = new HashMap<>();
    private int mfId = 1;

    public static void main(String[] args) {
        Main m = new Main();
        m.menuProcessor();

    }

    private void menuProcessor() {
        Scanner sc =  new Scanner(System.in);
        while (true) {
            System.out.println("Menu: \n" +
                    "1. Add manufacturer \n" +
                    "2. Add souvenir \n " +
                    "3. Look at all manufacturers \n" +
                    "4. Look at all souvenirs \n" +
                    "5. Edit manufacturer \n" +
                    "6. Edit souvenir \n" +
                    "7. Exit");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case (1):
                    mfId = ManufacturerService.addManufacturer(mfId, mf, sc);
                    break;
                case (3):
                    ManufacturerService.viewManufacturers(mf);
                    break;
                case (5):
                    ManufacturerService.editManufacturers(mf, sc);
                    break;
                case (7):
                    sc.close();
                    return;
            }
        }
    }
}
