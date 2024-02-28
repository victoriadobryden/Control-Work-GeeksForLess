package com.mydomain.main;

import com.mydomain.service.ManufacturerService;
import com.mydomain.service.SouvenirService;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private int mfId = 1;
    private int svId = 1;

    public static void main(String[] args) throws IOException {
        Main m = new Main();
        m.menuProcessor();
    }

    private void menuProcessor() throws IOException {
        Scanner sc =  new Scanner(System.in);
        while (true) {
            System.out.println("Menu: \n" +
                    "1. Add manufacturer \n" +
                    "2. Add souvenir \n" +
                    "3. Look at all manufacturers \n" +
                    "4. Look at all souvenirs \n" +
                    "5. Edit manufacturer \n" +
                    "6. Edit souvenir \n" +
                    "7. Print souvenirs of manufacturer \n" +
                    "8. Print souvenirs from country \n" +
                    "9. Print manufacturers with souvenir price less than \n" +
                    "10. Print all manufacturers and their souvenirs \n" +
                    "11. Print manufacturers of souvenir made in year \n" +
                    "12. Print souvenirs by year \n" +
                    "13. Delete manufacturer and souvenirs \n" +
                    "14. Exit");

            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case (1):
                    mfId = ManufacturerService.addManufacturer(mfId, sc);
                    break;
                case (2):
                    svId = SouvenirService.addSouvenir(sc, svId);
                    break;
                case (3):
                    ManufacturerService.viewManufacturers();
                    break;
                case (4):
                    SouvenirService.viewSouvenirs();
                    break;
                case (5):
                    ManufacturerService.editManufacturers(sc);
                    break;
                case (6):
                    SouvenirService.editSouvenirs(sc);
                    break;
                case (7):
                    SouvenirService.printSouvenirsOfManufacturer(sc);
                    break;
                case (8):
                    SouvenirService.printSouvenirsFromCountry(sc);
                    break;
                case (9):
                    ManufacturerService.printManufacturersWithSouvenirPriceLessThan(sc);
                    break;
                case (10):
                    ManufacturerService.printAllManufacturersAndTheirSouvenirs();
                    break;
                case (11):
                    ManufacturerService.printManufacturersOfSouvenirMadeInYear(sc);
                    break;
                case (12):
                    SouvenirService.printSouvenirsByYear();
                    break;
                case (13):
                    ManufacturerService.deleteManufacturerAndSouvenirs(sc);
                    break;
                case (14):
                    sc.close();
                    return;
            }
        }
    }
}
